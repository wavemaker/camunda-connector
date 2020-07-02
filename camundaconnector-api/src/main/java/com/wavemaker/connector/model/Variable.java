package com.wavemaker.connector.model;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 6/3/20
 */
public class Variable {

    private String value;
    private String type;

    public Variable(String value, String type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Variable{" +
                "value='" + value + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
