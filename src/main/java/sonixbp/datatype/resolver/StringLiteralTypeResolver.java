package sonixbp.datatype.resolver;

import sonixbp.datatype.type.StringLiteralType;

import java.util.Collection;
import java.util.Collections;


public class StringLiteralTypeResolver implements DatatypeResolver<StringLiteralType> {

    @Override
    public StringLiteralType deserializeType(String value) {
        return null;
    }

    @Override
    public String serializeType(StringLiteralType value) {
        return null;
    }
}
