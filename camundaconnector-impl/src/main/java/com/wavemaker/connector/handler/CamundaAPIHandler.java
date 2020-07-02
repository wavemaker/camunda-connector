package com.wavemaker.connector.handler;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.wavemaker.connector.helper.CamundaURLHelper;
import com.wavemaker.connector.model.ProcessRequest;
import com.wavemaker.connector.model.ProcessResponse;
import com.wavemaker.connector.model.Variable;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 6/3/20
 */
@Service
public class CamundaAPIHandler {

    private static final Logger logger = LoggerFactory.getLogger(CamundaAPIHandler.class);

    @Autowired
    CamundaURLHelper camundaURLHelper;

    RestTemplate restTemplate = new RestTemplate();

    public void validateProcessExist(String processName) throws ProcessDoesNotExistException {

        String processDefURL = camundaURLHelper.getProcessDefURL(processName);
        URI uri = toURI(processDefURL);
        try {
            logger.info(" Validating with camunda to verify camunda process is deployed", uri.toString());
            ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
            if (result.getStatusCodeValue() != 200) {
                throw new ProcessDoesNotExistException("Given process does not exist or deployed in camunda " + processName);
            }
        } catch (HttpClientErrorException ex) {
            logger.error("Failed to validate camunda process, either process does not deployed or payload isn't correct", ex);
            throw new RuntimeException("failed to execute camunda http request", ex);
        }

    }

    public ProcessResponse invokeProcess(ProcessRequest processRequest) {
        String processStartURL = camundaURLHelper.getProcessStartURL(processRequest.getProcessName());
        URI uri = toURI(processStartURL);
        JSONObject jsonObject = toRequestJSON(processRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);

        String result = restTemplate.postForObject(uri, request, String.class);
        return toResponseJSON(result);

    }

    private ProcessResponse toResponseJSON(String result) {
        ProcessResponse response = new ProcessResponse();
        Map<String, Variable> variableMap = new HashMap<>();

        JSONObject object = new JSONObject(result);
        response.setProcessInstance(object.getString("id"));
        JSONObject variables = object.getJSONObject("variables");
        if (variables != null) {
            for (String variable : object.getJSONObject("variables").keySet()) {

                JSONObject variable1 = variables.getJSONObject(variable);
                String type = variable1.getString("type");
                String value = String.valueOf(variable1.get("value"));
                variableMap.put(variable, new Variable(value, type));
            }
            response.setVariables(variableMap);
        }
        return response;
    }

    private JSONObject toRequestJSON(ProcessRequest processRequest) {
        JSONObject variables = new JSONObject();
        JSONObject main = new JSONObject();
        main.put("withVariablesInReturn", processRequest.getWithVariablesInReturn());

        for (Map.Entry<String, Variable> variable : processRequest.getVariables().entrySet()) {
            JSONObject obj = new JSONObject();
            obj.put("value", variable.getValue().getValue());
            obj.put("type", variable.getValue().getType());
            variables.put(variable.getKey(), obj);
        }
        main.put("variables", variables);
        return main;
    }

    private URI toURI(String processDefURL) {
        URI uri = null;
        try {
            uri = new URI(processDefURL);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return uri;
    }

    public void setCamundaURLHelper(CamundaURLHelper camundaURLHelper) {
        this.camundaURLHelper = camundaURLHelper;
    }
}
