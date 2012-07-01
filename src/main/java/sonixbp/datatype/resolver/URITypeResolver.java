package sonixbp.datatype.resolver;

import sonixbp.datatype.type.GemType;
import sonixbp.datatype.type.URIType;

import java.net.URI;
import java.net.URISyntaxException;


public class URITypeResolver implements GemTypeResolver<URIType> {

    @Override
    public URIType deserializeType(String value) {

        URIType type = new URIType();
        type.set(value);

        return type;
    }

    @Override
    public String serializeType(URIType value) {
        return value.get().toString();
    }

    @Override
    public boolean validate(URIType value) {

        return true;
    }
}
