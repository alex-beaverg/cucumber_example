package by.shift.context;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private static final Map<String, Object> map = new HashMap<>();

    public static void putContext(String key, Object value) {
        map.put(key, value);
    }

    public static Object getContext(String key) {
        return map.get(key);
    }
}
