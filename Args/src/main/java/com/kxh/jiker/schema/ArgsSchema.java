package com.kxh.jiker.schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArgsSchema {
    private Map<String, ArgSchemaDefinition> argSchemaDefinitions = new HashMap<>();

    public void loadArgSchemaDefinitions() {
        this.argSchemaDefinitions.put("l", new ArgSchemaDefinition("l", false));
        this.argSchemaDefinitions.put("p", new ArgSchemaDefinition("p", 8000));
        this.argSchemaDefinitions.put("d", new ArgSchemaDefinition("d", "/usr/logs"));
        this.argSchemaDefinitions.put("g", new ArgSchemaDefinition("g", new ArrayList<String>()));
    }

    public ArgSchemaDefinition getArgSchemaDefinition(String argKey) {
        return this.argSchemaDefinitions.get(argKey);
    }
}
