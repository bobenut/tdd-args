package com.kxh.jiker;

import java.util.HashMap;

public class ArgsContext {
    private HashMap<String, Object> context = new HashMap<>();

    public Object getValue(String key) {
        return context.get(key);
    }

    public void setValue(String key, Object value) {
        context.put(key, value);
    }

    public int getArgsCount() {
        return this.context.size();
    }
}
