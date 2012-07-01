package sonixbp.datatype.type;

/**
 * GemType for representing an IP
 */
public class IPType implements GemType<String> {

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
