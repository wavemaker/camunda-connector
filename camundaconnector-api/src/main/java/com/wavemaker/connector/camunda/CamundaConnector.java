package com.wavemaker.connector.camunda;

import com.wavemaker.connector.exception.ProcessDoesNotExistException;
import com.wavemaker.connector.model.ProcessRequest;
import com.wavemaker.connector.model.ProcessResponse;
import com.wavemaker.runtime.connector.annotation.WMConnector;


@WMConnector(name = "camunda",
        description = "This connector expose api to invoke camunda BPM process")
public interface CamundaConnector {

    ProcessResponse executeShortLivedProcess(ProcessRequest processRequest) throws ProcessDoesNotExistException;

}