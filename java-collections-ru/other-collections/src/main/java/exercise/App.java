package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// BEGIN

class App {
    public static Map<String, String> genDiff(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Map<String, String> diffs = new LinkedHashMap<>();

        Set<String> keys = new TreeSet(firstMap.keySet());
        keys.addAll(secondMap.keySet());

        for (String key: keys) {
            String diffResuls = analyseDiff(firstMap.get(key), secondMap.get(key));
            diffs.put(key, diffResuls);
        }
        return diffs;
    }

    public static String analyseDiff(Object value1, Object value2) {
        String convertedValue1 = String.valueOf(value1);
        String convertedValue2 = String.valueOf(value2);

        if (value1 == null && value2 != null) {
            return "added";
        } else if (value1 != null && value2 == null) {
            return "deleted";
        } else if (convertedValue1.equals(convertedValue2)) {
            return "unchanged";
        }
        return "changed";
    }
}
//END
