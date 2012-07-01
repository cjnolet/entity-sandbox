package sonixbp.datatype.resolver;

import sonixbp.datatype.type.URIType;

/**
 * Created by IntelliJ IDEA.
 * User: cnolet
 * Date: 7/1/12
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class URITypeResolver implements GemTypeResolver<URIType> {

    @Override
    public URIType deserializeType(String value) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String serializeType(URIType value) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean validate(URIType value) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
