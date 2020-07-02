package com.wavemaker.connector.camunda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.wavemaker.connector.camunda.CamundaConnector;
import com.wavemaker.connector.handler.CamundaAPIHandler;
import com.wavemaker.connector.handler.ProcessDoesNotExistException;
import com.wavemaker.connector.model.ProcessRequest;
import com.wavemaker.connector.model.ProcessResponse;


@Service
@Primary
public class CamundaConnectorImpl implements CamundaConnector{

    @Autowired
    private CamundaAPIHandler camundaAPIHandler;

    @Override
    public ProcessResponse executeShortLivedProcess(ProcessRequest processRequest) throws ProcessDoesNotExistException {

        //check process deployed in camunda
        camundaAPIHandler.validateProcessExist(processRequest.getProcessName());

        //create an instance of the process
        return camundaAPIHandler.invokeProcess(processRequest);
    }

}