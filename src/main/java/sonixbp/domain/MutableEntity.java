package sonixbp.domain;

import sonixbp.domain.support.Caretaker;

import java.util.Set;

public class MutableEntity implements Entity{

    private Entity entity;

    private Caretaker cartaker;

    public MutableEntity(Entity entity) {
        this.entity = entity;
        this.cartaker = new Caretaker();
    }

    public String getType() {
        return null;
    }

    public Set<EValue> getFullAttribute(String attribute) {
        return null;
    }

    public EValue getSingleAttribute(String attribute) {
        return null;
    }

    public Set<EValue> getFullRelationship(String relationship) {
        return null;
    }

    public EValue getSingleRelationship(String relationship) {
        return null;
    }

    public void addAttribute(EValue attribute) {
    }

    public void addRelationship(EValue relationship) {
    }

    public Set<String> getAttributeKeySet() {
        return null;
    }

    public Set<String> getRelationshipKeySet() {
        return null;
    }
}
