package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
class App {
    public static void swapKeyValue(KeyValueStorage kvStorage) {
        Map<String, String> kvData = kvStorage.toMap();
        for(Map.Entry<String, String> item : kvData.entrySet()) {
            kvStorage.unset(item.getKey());
            kvStorage.set(item.getValue(), item.getKey());
        }
    }
}
// END
