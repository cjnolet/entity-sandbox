package sonixbp.datatype.resolver;

import sonixbp.datatype.type.NonNegativeInteger;


public class NonNegativeIntegerResolver implements GemTypeResolver<NonNegativeInteger> {

    @Override
    public NonNegativeInteger deserializeType(String value) {
        return NonNegativeInteger.parseNonNegativeInteger(value);
    }

    @Override
    public String serializeType(NonNegativeInteger value) {
        return NonNegativeInteger.toString(value);
    }

    @Override
    public boolean validate(NonNegativeInteger value) {
        return value != null;
    }
}
