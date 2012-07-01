package sonixbp.datatype.type;

/**
 * GemType wrapping a native java String type
 */
public class StringLiteralType extends AbstractGemType<String> {

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
