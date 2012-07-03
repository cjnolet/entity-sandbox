package sonixbp.datatype.resolver;

/**
 * Resolver for serializing & deserializing Boolean types
 */
public class BooleanTypeResolver implements GemTypeResolver<Boolean> {

    @Override
    public Boolean deserializeType(String value) {
        return Boolean.parseBoolean(value);
    }

    @Override
    public String serializeType(Boolean value) {
        return Boolean.toString(value);
    }

    @Override
    public boolean validate(Boolean value) {
        return true;
    }
}
