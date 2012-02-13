package sonixbp.domain.mutable;

import sonixbp.domain.EValue;
import sonixbp.domain.Entity;
import sonixbp.schema.AttributeType;

import java.util.*;

public class EvalueMutationRepositoryImpl implements EValueMutationRepository {

    private Entity entity;
    private Map<String, Memento> pendingAdditions;
    private Map<String, Memento> pendingRemovals;

    public EvalueMutationRepositoryImpl(Entity entity) {
        this.entity = entity;
        this.init();
    }

    public void addAttribute(EValue attribute) {
        recordAddition(attribute, AttributeType.attribute);
    }

    public void updateAttribute(EValue attribute) {
        recordUpdate(attribute, AttributeType.attribute);
    }

    public void removeAttribute(EValue attribute) {
        recordRemoval(attribute, AttributeType.attribute);
    }

    public void addRelationship(EValue relationship) {
        recordAddition(relationship, AttributeType.relationship);
    }

    public void updateRelationship(EValue relationship) {
        recordUpdate(relationship, AttributeType.relationship);
    }

    public void removeRelationship(EValue relationship) {
        recordRemoval(relationship, AttributeType.relationship);
    }

    private void recordAddition(EValue eValue, AttributeType type) {
        pendingAdditions.put(eValue.getId(), new Memento(eValue, type));
    }

    // updates are embodied by removal(by id)+update
    private void recordUpdate(EValue eValue, AttributeType type) {
        recordRemoval(eValue, type);
        recordAddition(eValue, type);
    }

    private void recordRemoval(EValue eValue, AttributeType type) {
        pendingRemovals.put(eValue.getId(), new Memento(eValue, type));
    }

    public void clear() {
        this.init();
    }

    public void apply() {
        applyRemovals();
        applyAdditions();
    }

    private void applyRemovals() {
        for (Memento memento : pendingRemovals.values()) {
            if (memento.getType() == AttributeType.attribute) {
                applyAttributeRemoval(memento);
            } else {
                applyRelationshipRemoval(memento);
            }
        }
    }

    private void applyAdditions() {
        for (Memento memento : pendingAdditions.values()) {
            if (memento.getType() == AttributeType.attribute) {
                entity.addAttribute(memento.getValue());
            } else {
                entity.addRelationship(memento.getValue());
            }
        }
    }

    private void applyAttributeRemoval(Memento memento){
        EValue eValue = memento.getValue();
        Set<EValue> attributes = entity.getFullAttribute(eValue.getKey());

        if(attributes == null) return;

        List<EValue> removals = new ArrayList<EValue>();
        for(EValue attribute : attributes){
            removals.add(attribute);
        }

        for(EValue attribute : removals){
            attributes.remove(attribute);
        }
    }

    private void applyRelationshipRemoval(Memento memento){
        EValue eValue = memento.getValue();
        Set<EValue> relationships = entity.getFullRelationship(eValue.getKey());

        if(relationships == null) return;

        List<EValue> removals = new ArrayList<EValue>();
        for(EValue relationship : relationships){
            removals.add(relationship);
        }

        for(EValue relationship : removals){
            relationships.remove(relationship);
        }
    }

    private void init() {
        this.pendingRemovals = new LinkedHashMap<String, Memento>();
        this.pendingAdditions = new LinkedHashMap<String, Memento>();
    }


}
