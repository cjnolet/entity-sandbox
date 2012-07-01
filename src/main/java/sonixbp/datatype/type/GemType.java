package sonixbp.datatype.type;

/**
 * A GemType represents a normalized value with a generic datatype. It should have a default constructor and
 * set it's underlying value via the set() method.
 *
 * @param <T>
 */
public interface GemType<T> {

    /**
     * Returns a string representation of the underlying value. This should not be a serialized version.
     * @return
     */
    String getAsString();

    /**
     * Returns the native type associated with a GemType implementor
     * @return
     */
    T get();

    /**
     * Sets the underlying value to the native type of the GemType implementor
     * @param value
     */
    void set(T value);
}
