package sonixbp.datatype.resolver;


public class StringLiteralTypeResolver implements GemTypeResolver<String> {

    @Override
    public String deserializeType(String value) {
        return value;
    }

    @Override
    public String serializeType(String value) {
        return value;
    }

    @Override
    public boolean validate(String value) {
        return true;
    }
}
