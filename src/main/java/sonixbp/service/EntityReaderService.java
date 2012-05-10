package sonixbp.service;

import com.sun.tools.javac.util.Pair;
import sonixbp.domain.BasicEntity;

import java.util.Collection;


public interface EntityReaderService {

    BasicEntity getByTypeAndId(String type, String id);

    Collection<BasicEntity> getAllByType(String type);

    Collection<BasicEntity> getAllByTypesAndAttributes(Collection<Pair<String,String>> typeAttributePairs);

    Collection<BasicEntity> getAllByTypesAndRelationships(Collection<Pair<String,String>> typeRelationshipPairs);

}


