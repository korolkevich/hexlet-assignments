package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
class InMemoryKV implements KeyValueStorage {
    private Map<String, String> memoryKV;

    public InMemoryKV(Map<String, String> initData) {
        this.memoryKV = new HashMap<String, String>(initData);
    }

    public void set(String key, String value) {
        this.memoryKV.put(key, value);
    }

    public void unset(String key) {
        String value = this.memoryKV.get(key);
        if (value != null) {
            this.memoryKV.remove(key);
        }
    }

    public String get(String key, String defaultValue) {
        String value = this.memoryKV.get(key);
        if (value != null) {
            return value;
        }
        return defaultValue;
    }

    public Map<String, String> toMap() {
        return new HashMap<String, String>(this.memoryKV);
    }
}
// END
