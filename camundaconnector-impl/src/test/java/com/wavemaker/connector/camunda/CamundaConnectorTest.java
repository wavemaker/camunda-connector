package com.wavemaker.connector.camunda;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import com.wavemaker.connector.camunda.CamundaConnector;
import com.wavemaker.connector.model.ProcessRequest;
import com.wavemaker.connector.model.ProcessResponse;
import com.wavemaker.connector.model.Variable;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CamundaConnectorTestConfiguration.class)
public class CamundaConnectorTest {

    @Autowired
    CamundaConnector camundaConnector;

    @Test
    public void executeShortLivedProcess() {

        Map<String, Variable> variables = new HashMap<>();
        variables.put("cityname", new Variable("chennai", "String"));

        ProcessRequest processRequest = new ProcessRequest();
        processRequest.setProcessName("someprocess");
        processRequest.setVariables(variables);
        processRequest.setWithVariablesInReturn(true);
        ProcessResponse response = camundaConnector.executeShortLivedProcess(processRequest);

        System.out.println("short lived process respose: " + response);

    }
}