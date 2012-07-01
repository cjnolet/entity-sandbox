package sonixbp.datatype.type;


/**
 * Provides common centralized methods
 * @param <T>
 */
public abstract class AbstractGemType<T> implements GemType<T> {


    /**
     * A pretty print version that can safely apply to all subclasses
     * @return
     */
    public String toString() {
        return new StringBuffer("gemType: [typeClass=").append(getClass().getName()).append(", wrappedType=")
                .append(get().getClass().getName()).append(", valueAsString=")
                .append(getAsString()).append("]").toString();
    }
}
