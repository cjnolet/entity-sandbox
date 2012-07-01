package sonixbp.datatype.resolver;

import sonixbp.datatype.type.LongType;

public class LongTypeResolver implements GemTypeResolver<LongType> {


    @Override
    public LongType deserializeType(String value) {
        LongType type = new LongType();
        type.set(Long.parseLong(value));

        return type;
    }

    @Override
    public String serializeType(LongType value) {
        return Long.toString(value.get());
    }

    @Override
    public boolean validate(LongType value) {
        return true;
    }
}
