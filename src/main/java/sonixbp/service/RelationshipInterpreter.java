package sonixbp.service;

import sonixbp.exception.ArtifactNotFoundException;

import java.io.InputStream;
import java.net.URI;

public interface RelationshipInterpreter<T> {

    T interpret(URI link) throws ArtifactNotFoundException;

    boolean artifactExists(URI link);
}
