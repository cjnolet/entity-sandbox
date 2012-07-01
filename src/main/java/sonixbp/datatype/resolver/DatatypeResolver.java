package sonixbp.datatype.resolver;

import sonixbp.datatype.type.GemType;

/**
 * Maps a [typed] raw value into a serialized form and vice versa
 * @param <T>
 */
public interface DatatypeResolver<T extends GemType> {

    /**
     * A deserialized form should return the raw object
     * @param value
     * @return
     */
    T deserializeType(String value);

    /**
     *
     * @param value
     * @return
     */
    String serializeType(T value);


    boolean validate(T value);
}
