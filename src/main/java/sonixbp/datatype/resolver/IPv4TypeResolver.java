package sonixbp.datatype.resolver;


import sonixbp.datatype.type.IPv4;

public class IPv4TypeResolver implements GemTypeResolver<IPv4> {

    @Override
    public IPv4 deserializeType(String value) {

        return new IPv4(Long.parseLong(value));
    }

    @Override
    public String serializeType(IPv4 value) {

        return String.format("%010d", value.getValue());
    }

    @Override
    public boolean validate(IPv4 value) {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
