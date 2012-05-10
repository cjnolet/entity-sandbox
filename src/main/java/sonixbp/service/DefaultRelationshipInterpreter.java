package sonixbp.service;

import com.sun.activation.registries.MimeTypeEntry;
import sonixbp.exception.ArtifactNotFoundException;

import javax.activation.MimeType;
import java.net.URI;


public class DefaultRelationshipInterpreter<T> implements RelationshipInterpreter<T> {

    public T interpret(URI link) throws ArtifactNotFoundException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean artifactExists(URI link) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
