package com.kxh.jiker;

import com.kxh.jiker.handler.ArgDirectoryParserHandler;
import com.kxh.jiker.handler.ArgGParserHandler;
import com.kxh.jiker.handler.ArgLoggingParserHandler;
import com.kxh.jiker.handler.ArgPortParserHandler;
import com.kxh.jiker.schema.ArgsSchema;

public class ArgsParser {
    public static ArgsSchema createSchema() {
        ArgsSchema argsSchema = new ArgsSchema();
        argsSchema.loadArgSchemaDefinitions();
        return argsSchema;
    }

    public static ArgsParserExecutor createExecutor() {
        ArgsParserExecutor argsParserExecutor = new ArgsParserExecutor();
        argsParserExecutor.registHandlers(new ArgLoggingParserHandler());
        argsParserExecutor.registHandlers(new ArgPortParserHandler());
        argsParserExecutor.registHandlers(new ArgDirectoryParserHandler());
        argsParserExecutor.registHandlers(new ArgGParserHandler());
        return argsParserExecutor;
    }


    public static ArgsContext buildContext(String[] args, ArgsParserExecutor exxcutor, ArgsSchema schema) {
        ArgsContext context = new ArgsContext();
        exxcutor.parse(args, context, schema);
        return context;
    }
}
