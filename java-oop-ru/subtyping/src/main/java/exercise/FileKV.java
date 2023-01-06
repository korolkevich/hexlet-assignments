package exercise;

// BEGIN
import java.nio.file.Path;
import java.util.Map;


class FileKV implements KeyValueStorage {
    private String pathMemoryFile;

    public FileKV(String pathMemoryFile, Map<String, String> initData) {
        this.pathMemoryFile = pathMemoryFile;
        saveData(initData);
    }

    public void set(String key, String value) {
        Map<String, String> jsonData = readData();
        jsonData.put(key, value);
        saveData(jsonData);
    }

    public void unset(String key) {
        Map<String, String> jsonData = readData();
        String value = jsonData.get(key);
        if (value != null) {
            jsonData.remove(key);
            saveData(jsonData);
        }
    }

    public String get(String key, String defaultValue) {
        Map<String, String> jsonData = readData();
        String value = jsonData.get(key);
        if (value != null) {
            return value;
        }
        return defaultValue;
    }

    public Map<String, String> toMap() {
        return readData();
    }

    private void saveData(Map<String, String> internalData) {
        String rawData = Utils.serialize(internalData);
        Utils.writeFile(pathMemoryFile, rawData);
    }

    private Map<String, String> readData() {
        String rawData = Utils.readFile(this.pathMemoryFile);
        return Utils.unserialize(rawData);
    }
}
// END
