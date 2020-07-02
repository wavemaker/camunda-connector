package com.wavemaker.connector.model;

import java.util.Map;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 6/3/20
 */
public class ProcessResponse {

    private String processInstance;

    Map<String,Variable> variables;

    public String getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(String processInstance) {
        this.processInstance = processInstance;
    }

    public Map<String, Variable> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Variable> variables) {
        this.variables = variables;
    }

    @Override
    public String toString() {
        return "ProcessResponse{" +
                "processInstance='" + processInstance + '\'' +
                ", variables=" + variables +
                '}';
    }
}
