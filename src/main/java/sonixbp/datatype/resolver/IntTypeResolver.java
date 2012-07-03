package sonixbp.datatype.resolver;

/**
 * Resolver for serializing and deserializing Integer types
 */
public class IntTypeResolver implements GemTypeResolver<Integer> {

    @Override
    public Integer deserializeType(String value) {

       return Integer.parseInt(value);
    }

    @Override
    public String serializeType(Integer value) {
        return Integer.toString(value);
    }

    @Override
    public boolean validate(Integer value) {
        return true;
    }
}
