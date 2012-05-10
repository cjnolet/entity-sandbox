package sonixbp.service;

import sonixbp.domain.BasicEntity;
import sonixbp.exception.ArtifactNotFoundException;

import sonixbp.domain.Entity;
import java.io.InputStream;
import java.net.URI;
import java.util.Date;

public class EntityRelationshipInterpreter<T extends BasicEntity> implements RelationshipInterpreter<T> {

    private EntityService entityService;

    public EntityRelationshipInterpreter(EntityService entityService) {

        this.entityService = entityService;
    }

    public T interpret(URI link) throws ArtifactNotFoundException {
        return (T)new Entity("id", "type");
    }

    public boolean artifactExists(URI link) {
        return false;
    }
}
