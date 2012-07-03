package sonixbp.datatype.resolver;


public class ByteTypeResolver implements GemTypeResolver<Byte> {

    @Override
    public Byte deserializeType(String value) {
        return Byte.parseByte(value);
    }

    @Override
    public String serializeType(Byte value) {
        return Byte.toString(value);
    }

    @Override
    public boolean validate(Byte value) {
        return true;
    }
}
