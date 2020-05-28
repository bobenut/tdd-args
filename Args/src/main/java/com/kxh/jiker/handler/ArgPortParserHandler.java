package com.kxh.jiker.handler;

import com.kxh.jiker.ArgsContext;
import com.kxh.jiker.schema.ArgsSchema;

public class ArgPortParserHandler extends ArgParserHandler {

    public static final String ARG_KEY = "p";

    @Override
    public boolean parse(String argKey, String argValue, ArgsContext context, ArgsSchema schema) {
        // -p 参数只能接受integer类型的参数
        if (argValue != null && !isStringIntegerValue(argValue)) {
            throw new IllegalArgumentException(String.format("Arg type of -%s should be integer", argKey));
        }

        Integer argValueOfInteger;
        if (argValue == null) {
            argValueOfInteger = (Integer) schema.getArgSchemaDefinition(ARG_KEY).getDefaultValue();
        } else {
            argValueOfInteger = Integer.valueOf(argValue);
        }

        context.setValue(argKey, argValueOfInteger);

        return true;
    }

    @Override
    public boolean shouldHandle(String argKey) {
        return ARG_KEY.equals(argKey);
    }
}
