package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class PairedTag extends Tag{
    private String contentBody;
    private List<Tag> childs;

    public PairedTag(
            String name,
            Map<String,String> attributes,
            String contentBody,
            List<Tag> childs
    ) {
        super();
        this.name = name;
        this.attributes = attributes;
        this.contentBody = contentBody;
        this.childs = childs;

    }
    public String toString() {
        StringBuilder pairedTagString = new StringBuilder(createSingle());

        for(Tag child: this.childs) {
            pairedTagString.append(child.toString());
        }
        pairedTagString.append(this.contentBody);
        pairedTagString.append("</" + this.name + ">");
        return pairedTagString.toString();
    }

    private String createSingle() {
        StringBuilder pairedTagString = new StringBuilder("<");
        pairedTagString.append(this.name);

        for(String key: this.attributes.keySet()) {
            pairedTagString.append(" " + key + "=\"" + this.attributes.get(key) + "\"");
        }
        pairedTagString.append(">");
        return pairedTagString.toString();
    }
}
// END
