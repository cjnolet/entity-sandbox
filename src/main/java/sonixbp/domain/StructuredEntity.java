package sonixbp.domain;

import java.util.Map;
import java.util.Set;

import sonixbp.schema.EntitySchema;
import sonixbp.schema.EntitySchemaValidator;
import sonixbp.schema.ValidationErrorType;

public class StructuredEntity implements BasicEntity, Validateable {

    BasicEntity entity;
    EntitySchema schema;
    EntitySchemaValidator validator;

    public StructuredEntity(String schemaFilename, BasicEntity entity) {

		this.entity = entity;
		this.schema = EntitySchema.parseEntitySchemaFromResource(schemaFilename, entity.getType());
        this.validator = new EntitySchemaValidator(schema, entity);
    }

    public String getType() {
        return entity.getType();
    }

	public Set<Attribute> getFullAttribute(String attribute) {

		return entity.getFullAttribute(attribute);
		
	}

	public Attribute getSingleAttribute(String attribute) {
		return entity.getSingleAttribute(attribute);
	}

	public Set<Relationship> getFullRelationship(String relationship) {

		return entity.getFullRelationship(relationship);
	}

	public Relationship getSingleRelationship(String relationship) {
		return entity.getSingleRelationship(relationship);
	}

	public void addAttribute(Attribute attribute) {
		
		entity.addAttribute(attribute);
	}

	public void addRelationship(Relationship relationship) {
		entity.addRelationship(relationship);
	}
	
	public boolean validate() {

		return validator.validate();
	}
	
	public EntitySchemaValidator getValidator() {
		
		return validator;
	}

	public Set<String> getAttributeKeySet() {
		
		return entity.getAttributeKeySet();
	}

	public Set<String> getRelationshipKeySet() {
		
		return entity.getRelationshipKeySet();
	}

	public void updateAttribute(Attribute attribute) {
		
		entity.updateAttribute(attribute);
		
	}

	public void updateRelationship(Relationship relationship) {
		
		entity.updateRelationship(relationship);
		
	}

	public void deleteAttribute(Attribute attribute) {
		
		entity.deleteAttribute(attribute);
		
	}

	public void deleteRelationship(Relationship relationship) {
		entity.deleteRelationship(relationship);
	}

	public Map<Tuple, Set<ValidationErrorType>> getErrors() {
		return validator.getErrors();
	}
}
