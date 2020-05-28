package com.kxh.jiker.schema;

public class ArgSchemaDefinition {
    private String key = "";
    private Object defaultValue;

    public ArgSchemaDefinition(String key, Object defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }
}
