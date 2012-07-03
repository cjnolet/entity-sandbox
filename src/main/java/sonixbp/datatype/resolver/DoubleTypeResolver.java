package sonixbp.datatype.resolver;


public class DoubleTypeResolver implements GemTypeResolver<Double> {

    @Override
    public Double deserializeType(String value) {
        return Double.parseDouble(value);
    }

    @Override
    public String serializeType(Double value) {
        return Double.toString(value);
    }

    @Override
    public boolean validate(Double value) {
        return true;
    }
}
