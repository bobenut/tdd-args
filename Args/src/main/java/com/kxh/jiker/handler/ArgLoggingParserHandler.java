package com.kxh.jiker.handler;

import com.kxh.jiker.ArgsContext;
import com.kxh.jiker.schema.ArgsSchema;

public class ArgLoggingParserHandler extends ArgParserHandler {

    public static final String ARG_KEY = "l";

    @Override
    public boolean parse(String argKey, String argValue, ArgsContext context, ArgsSchema schema) {
        // -l 参数只能接受boolean类型的参数
        if (argValue != null && !isStringBooleanValue(argValue)) {
            throw new IllegalArgumentException(String.format("Arg type of -%s should be boolean", argKey));
        }

        boolean argValueOfBool;
        if (argValue == null) {
            argValueOfBool = (boolean) schema.getArgSchemaDefinition(ARG_KEY).getDefaultValue();
        } else {
            argValueOfBool = Boolean.valueOf(argValue);
        }

        context.setValue(argKey, argValueOfBool);

        return true;
    }

    @Override
    public boolean shouldHandle(String argKey) {
        return ARG_KEY.equals(argKey);
    }
}
