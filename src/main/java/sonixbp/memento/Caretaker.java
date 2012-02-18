package sonixbp.memento;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import sonixbp.domain.Attribute;
import sonixbp.domain.BasicEntity;
import sonixbp.domain.Tuple;
import sonixbp.domain.Relationship;

public class Caretaker {

    private Map<String, Set<Memento>> attributes;
    private Map<String, Set<Memento>> relationships;

    public Caretaker(BasicEntity entity) {
        this.reset();
        capture(entity);
    }

    public void reset(){
        this.attributes = new HashMap<String, Set<Memento>>();
        this.relationships = new HashMap<String, Set<Memento>>();
    }

    private void capture(BasicEntity entity) {
        Set<String> attrKeys = entity.getAttributeKeySet();
        for (String key : attrKeys) {
            Set<Attribute> attrs = entity.getFullAttribute(key);
            Set<Memento> mementos = new HashSet<Memento>();
            for (Tuple attr : attrs) {
                mementos.add(new Memento(attr));
            }

            attributes.put(key, mementos);
        }

        Set<String> relKeys = entity.getRelationshipKeySet();
        for (String key : relKeys) {
            Set<Relationship> rels = entity.getFullRelationship(key);
            Set<Memento> mementos = new HashSet<Memento>();
            for (Tuple rel : rels) {
                mementos.add(new Memento(rel));
            }

            relationships.put(key, mementos);
        }
    }
}
