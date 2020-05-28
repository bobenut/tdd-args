package com.kxh.jiker;

import com.kxh.jiker.handler.ArgParserHandler;
import com.kxh.jiker.schema.ArgsSchema;

import java.util.ArrayList;
import java.util.List;

public class ArgsParserExecutor {
    List<ArgParserHandler> handlers = new ArrayList<>();

    public void registHandlers(ArgParserHandler handler) {
        this.handlers.add(handler);
    }

    public void parse(String[] args, ArgsContext context, ArgsSchema schema) {
        for (int i = 0; i < args.length; ) {
            String arg = args[i];
            String argKey = String.valueOf(arg.charAt(1));

            // 判断标识是否合法，是否是”-“开头，并且是否是被定义过的
            isArgKeyIllegal(schema, arg, argKey);

            int forSteps = 1;
            String argValue = null;
            // 判断下一个arg是不是值，是值的话就当作参数值，并设置下一次循环直接跳过参数值
            if (isNextArgValue(args, i)) {
                argValue = args[i + 1];
                forSteps = 2;
            }

            // 调用每一个arg处理器，处理自身的arg
            doHandlers(context, schema, argKey, argValue);

            i += forSteps;
        }
    }

    private boolean isNextArgValue(String[] args, int index) {
        return index < args.length - 1 && !isArgKey(args[index + 1]);
    }

    private void doHandlers(ArgsContext context, ArgsSchema schema, String argKey, String argValue) {
        for (ArgParserHandler handler : this.handlers) {
            if (handler.shouldHandle(argKey)) {
                handler.parse(argKey, argValue, context, schema);
            }
        }
    }

    private void isArgKeyIllegal(ArgsSchema schema, String arg, String argKey) {
        if (!isArgKey(arg) || !isArgKeyDefined(argKey, schema)) {
            throw new IllegalArgumentException(String.format("Unable to identify parameter %s", arg));
        }
    }

    private boolean isArgKeyDefined(String argKey, ArgsSchema argsSchema) {
        if (argsSchema.getArgSchemaDefinition(argKey) != null) {
            return true;
        }

        return false;
    }

    private boolean isArgKey(String arg) {
        return arg.startsWith("-");
    }
}
