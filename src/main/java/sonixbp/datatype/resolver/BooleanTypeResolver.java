package sonixbp.datatype.resolver;

import sonixbp.datatype.type.BooleanType;

/**
 * Resolver for serializing & deserializing Boolean types
 */
public class BooleanTypeResolver implements DatatypeResolver<BooleanType> {
    @Override
    public BooleanType deserializeType(String value) {
        BooleanType type = new BooleanType();
        type.set(Boolean.parseBoolean(value));
        return type;
    }

    @Override
    public String serializeType(BooleanType value) {
        return Boolean.toString(value.get());
    }
}
