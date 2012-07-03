package sonixbp.datatype.resolver;

public class LongTypeResolver implements GemTypeResolver<Long> {


    @Override
    public Long deserializeType(String value) {
        return Long.parseLong(value);
    }

    @Override
    public String serializeType(Long value) {
        return Long.toString(value);
    }

    @Override
    public boolean validate(Long value) {
        return true;
    }
}
