package com.kxh.jiker.handler;

import com.kxh.jiker.ArgsContext;
import com.kxh.jiker.schema.ArgsSchema;

public class ArgDirectoryParserHandler extends ArgParserHandler {

    public static final String ARG_KEY = "d";

    @Override
    public boolean parse(String argKey, String argValue, ArgsContext context, ArgsSchema schema) {
        String argValueOfString = argValue;
        if (argValue == null) {
            argValueOfString = (String) schema.getArgSchemaDefinition(ARG_KEY).getDefaultValue();
        }

        context.setValue(argKey, argValueOfString);
        return true;
    }

    @Override
    public boolean shouldHandle(String argKey) {
        return ARG_KEY.equals(argKey);
    }
}
