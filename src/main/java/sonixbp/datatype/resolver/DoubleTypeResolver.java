package sonixbp.datatype.resolver;


import sonixbp.datatype.mapping.GemTypeContext;
import sonixbp.datatype.type.DoubleType;


public class DoubleTypeResolver implements GemTypeResolver<DoubleType> {

    @Override
    public DoubleType deserializeType(String value) {
        DoubleType type = new DoubleType();
        type.set(Double.parseDouble(value));

        return type;
    }

    @Override
    public String serializeType(DoubleType value) {
        return Double.toString(value.get());
    }

    @Override
    public boolean validate(DoubleType value) {
        return true;
    }
}
