package sonixbp.domain.mutable;

import sonixbp.domain.EValue;
import sonixbp.domain.Entity;

import java.util.Set;

public class MutableEntityImpl implements MutableEntity{

    private Entity entity;

    private EValueMutationRepository repository;

    public MutableEntityImpl(Entity entity) {
        this.entity = entity;
        repository = new EvalueMutationRepositoryImpl(entity);
    }

    public String getType() {
        return entity.getType();
    }

    public Set<EValue> getFullAttribute(String attribute) {
        return entity.getFullAttribute(attribute);
    }

    public EValue getSingleAttribute(String attribute) {
        return entity.getSingleAttribute(attribute);
    }

    public Set<EValue> getFullRelationship(String relationship) {
        return entity.getFullRelationship(relationship);
    }

    public EValue getSingleRelationship(String relationship) {
        return entity.getSingleRelationship(relationship);
    }

    public void addAttribute(EValue attribute) {
        repository.addAttribute(attribute);
    }

    public void updateAttribute(EValue attribute) {
        repository.updateAttribute(attribute);
    }

    public void removeAttribute(EValue attribute) {
        repository.removeAttribute(attribute);
    }

    public void addRelationship(EValue relationship) {
        repository.addRelationship(relationship);
    }

    public void updateRelationship(EValue relationship) {
        repository.updateRelationship(relationship);
    }

    public void removeRelationship(EValue relationship) {
        repository.removeRelationship(relationship);
    }

    public Set<String> getAttributeKeySet() {
        return entity.getAttributeKeySet();
    }

    public Set<String> getRelationshipKeySet() {
        return entity.getRelationshipKeySet();
    }

    public void apply() {
        repository.apply();
    }

    public void undo() {
        repository.clear();
    }
}
