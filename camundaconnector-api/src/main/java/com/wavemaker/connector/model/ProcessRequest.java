package com.wavemaker.connector.model;

import java.util.Map;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 6/3/20
 */
public class ProcessRequest {

    String processName;

    Map<String,Variable> variables;

    Boolean withVariablesInReturn;

    public Map<String, Variable> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Variable> variables) {
        this.variables = variables;
    }

    public Boolean getWithVariablesInReturn() {
        return withVariablesInReturn;
    }

    public void setWithVariablesInReturn(Boolean withVariablesInReturn) {
        this.withVariablesInReturn = withVariablesInReturn;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
}
