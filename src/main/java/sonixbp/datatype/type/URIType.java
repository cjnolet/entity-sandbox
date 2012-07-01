package sonixbp.datatype.type;

import java.net.URI;

public class URIType extends AbstractGemType<String> {

    String value;

    @Override
    public String getAsString() {
        return value;
    }

    @Override
    public String get() {
        return value;
    }

    @Override
    public void set(String value) {

        this.value = value;
    }
}
