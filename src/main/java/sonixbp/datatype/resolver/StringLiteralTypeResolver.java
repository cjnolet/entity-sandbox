package sonixbp.datatype.resolver;

import sonixbp.datatype.type.StringLiteralType;

import java.util.Collection;
import java.util.Collections;


public class StringLiteralTypeResolver implements DatatypeResolver<StringLiteralType> {

    @Override
    public StringLiteralType deserializeType(String value) {

        StringLiteralType type = new StringLiteralType();
        type.set(value);
        return type;
    }

    @Override
    public String serializeType(StringLiteralType value) {
        return value.get();
    }

    @Override
    public boolean validate(StringLiteralType value) {
        return true;
    }
}
