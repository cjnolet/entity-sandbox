package sonixbp.datatype.resolver;

import sonixbp.datatype.type.URIType;


public class RelationshipTypeResolver implements GemTypeResolver<URIType>{

    @Override
    public URIType deserializeType(String value) {
        return null;
    }

    @Override
    public String serializeType(URIType value) {
        return null;
    }

    @Override
    public boolean validate(URIType value) {

        return true;
    }
}
