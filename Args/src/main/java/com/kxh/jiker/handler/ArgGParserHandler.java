package com.kxh.jiker.handler;

import com.kxh.jiker.ArgsContext;
import com.kxh.jiker.schema.ArgsSchema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ArgGParserHandler extends ArgParserHandler {

    public static final String ARG_KEY = "g";

    @Override
    public boolean parse(String argKey, String argValue, ArgsContext context, ArgsSchema schema) {

        ArrayList<String> argValueOfStringList;
        if (argValue == null) {
            argValueOfStringList =
                    (ArrayList<String>) schema.getArgSchemaDefinition(ARG_KEY).getDefaultValue();
        } else {
            argValueOfStringList =
                    (ArrayList<String>) Arrays.stream(argValue.split(",")).collect(Collectors.toList());
        }

        context.setValue(argKey, argValueOfStringList);

        return true;
    }

    @Override
    public boolean shouldHandle(String argKey) {
        return ARG_KEY.equals(argKey);
    }
}
