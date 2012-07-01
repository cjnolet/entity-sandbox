package sonixbp.datatype.type;

/**
 * GemType wrapping a native java Integer value
 */
public class IntType implements GemType<Integer> {

    Integer value;

    @Override
    public String getAsString() {
        return null;
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
