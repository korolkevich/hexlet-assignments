package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static Map<String, Integer> getWordCount(String groupWords) {
        String[] words = groupWords.split(" ");
        Map<String, Integer> cntWords = new HashMap<>();

        if (groupWords.length() == 0) {
            return cntWords;
        }

        for (String word: words) {
            var isExistWord = cntWords.get(word);
            if (isExistWord == null) {
                cntWords.put(word, 1);
            } else {
                cntWords.put(word, isExistWord + 1);
            }
        }

        return cntWords;
    }

    public static String toString(Map<String, Integer> words) {
        StringBuilder mapAsString = new StringBuilder("{\n");
        int cntAppends = 0;

        for (Map.Entry<String, Integer> word: words.entrySet()) {
            var mapKey = word.getKey();
            if (!mapKey.equals("")) {
                mapAsString.append("  " + mapKey + ": " + word.getValue() + "\n");
                cntAppends += 1;
            }
        }
        mapAsString.append("}");

        if (cntAppends == 0) {
            return "{}";
        }
        return mapAsString.toString();
    }
}
//END
