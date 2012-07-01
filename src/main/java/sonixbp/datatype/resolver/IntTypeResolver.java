package sonixbp.datatype.resolver;

import sonixbp.datatype.type.IntType;

/**
 * Resolver for serializing and deserializing Integer types
 */
public class IntTypeResolver implements DatatypeResolver<IntType> {

    @Override
    public IntType deserializeType(String value) {

        IntType type = new IntType();
        type.set(Integer.parseInt(value));
        return type;
    }

    @Override
    public String serializeType(IntType value) {
        return Integer.toString(value.get());
    }

    @Override
    public boolean validate(IntType value) {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
