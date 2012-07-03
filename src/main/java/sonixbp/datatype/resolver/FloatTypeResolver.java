package sonixbp.datatype.resolver;

public class FloatTypeResolver implements GemTypeResolver<Float> {

    @Override
    public Float deserializeType(String value) {
        return Float.parseFloat(value);
    }

    @Override
    public String serializeType(Float value) {
        return Float.toString(value);
    }

    @Override
    public boolean validate(Float value) {
        return value != null;
    }
}
