package com.wavemaker.connector.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 6/3/20
 */
@Service
public class CamundaURLHelper {

    public static final String HTTP = "http://";
    public static final String Semicolon = ":";

    @Value("${camunda.server.host}")
    private String host;

    @Value("${camunda.server.port}")
    private String port;

    private String START_PROCESS_URL = "/engine-rest/process-definition/key/${processname}/start";

    private String PROCESS_DEFINITION = "/engine-rest/process-definition/key/${processname}";

    public String   getProcessStartURL(String processName) {
        String preciseURL = START_PROCESS_URL.replace("${processname}", processName);
        return getconciseURL(preciseURL);
    }

    public String getProcessDefURL(String processName){
        String preciseURL = PROCESS_DEFINITION.replace("${processname}", processName);
        return getconciseURL(preciseURL);
    }



    private String getconciseURL(String preciseURL) {
        return HTTP + host + Semicolon + port + preciseURL;
    }
}
