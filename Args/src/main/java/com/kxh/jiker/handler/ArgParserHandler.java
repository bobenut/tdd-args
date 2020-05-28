package com.kxh.jiker.handler;

import com.kxh.jiker.ArgsContext;
import com.kxh.jiker.schema.ArgsSchema;

public abstract class ArgParserHandler {
    public abstract boolean parse(String argKey, String argValue, ArgsContext context, ArgsSchema schema);

    public abstract boolean shouldHandle(String argKey);

    public boolean isStringBooleanValue(String value) {
        if (value == null || "".equals(value)) return false;

        String lowercaseValue = value.toLowerCase();
        if (!"true".equals(lowercaseValue) && !"false".equals(lowercaseValue)) return false;

        return true;
    }

    public boolean isStringIntegerValue(String value) {
        if (value == null || "".equals(value)) return false;
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
