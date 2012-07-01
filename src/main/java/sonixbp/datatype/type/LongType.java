package sonixbp.datatype.type;

/**
 * GemType wrapping a native java Long type
 */
public class LongType implements GemType<Long> {

    private Long value;

    @Override
    public String getAsString() {
        return Long.toString(value);
    }

    @Override
    public Long get() {
        return value;
    }

    @Override
    public void set(Long value) {

        this.value = value;
    }
}
