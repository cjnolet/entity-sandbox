package sonixbp.datatype.type;

/**
 * GemType wrapping a native java Integer value
 */
public class IntType extends AbstractGemType<Integer> {

    Integer value;

    @Override
    public String getAsString() {
        return Integer.toString(value);
    }

    @Override
    public Integer get() {
        return value;
    }

    @Override
    public void set(Integer value) {
        this.value = value;
    }
}
