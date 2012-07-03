package sonixbp.datatype.resolver;

import java.net.URI;
import java.net.URISyntaxException;


public class URITypeResolver implements GemTypeResolver<URI> {

    @Override
    public URI deserializeType(String value) {

        try {
            URI uri = new URI(value);

            return uri;
        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;
    }

    @Override
    public String serializeType(URI value) {
        return value.toASCIIString();
    }

    @Override
    public boolean validate(URI value) {

        return value != null;
    }
}
