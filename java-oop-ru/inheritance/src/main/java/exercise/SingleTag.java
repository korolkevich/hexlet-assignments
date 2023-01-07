package exercise;

import java.util.Map;

// BEGIN
class SingleTag extends Tag {
    public SingleTag(String name, Map<String, String> attributes) {
        super();
        this.name = name;
        this.attributes = attributes;
    }

    public String toString() {
        StringBuilder mapAsString = new StringBuilder("<");
        mapAsString.append(this.name);

        for(String key: this.attributes.keySet()) {
            mapAsString.append(" " + key + "=\"" + this.attributes.get(key) + "\"");
        }
        mapAsString.append(">");
        return mapAsString.toString();
    }
}
// END
