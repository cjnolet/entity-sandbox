package sonixbp.domain;

import java.util.HashSet;
import java.util.Set;

import sonixbp.exception.AttributeNotDefinedException;
import sonixbp.exception.MissingSchemaException;
import sonixbp.exception.RequiredAttributeMissingException;
import sonixbp.schema.EntitySchema;
import sonixbp.schema.EntitySchemaValidator;
import sonixbp.util.EntitySchemaUtils;

public class StructuredEntity implements BasicEntity, Validateable {

    BasicEntity entity;
    EntitySchema schema;
    EntitySchemaValidator validator;

    public StructuredEntity(BasicEntity entity) {

        schema = EntitySchemaUtils.parseEntitySchemaFromResource(entity.getType());
        this.entity = entity;

        if(schema == null) {
        	
            throw new MissingSchemaException();
        }
        
        else {
            this.validator = new EntitySchemaValidator(schema, entity);
        }
    }

    public String getType() {
        return entity.getType();
    }

	public Set<Attribute> getFullAttribute(String attribute) {

		Set<Attribute> values = entity.getFullAttribute(attribute);
		
		if(schema == null) {

			throw new MissingSchemaException();
		}
		
		else if(schema.getAttribute(attribute) == null) {
			
			throw new AttributeNotDefinedException();
		}
		
		else {
			if(values == null) {
				
				String defaultValue = schema.getDefaultValueForAttribute(attribute);
				
				if(defaultValue != null) {
					
					Set<Attribute> retVals = new HashSet<Attribute>();
					
					Attribute value = new Attribute(attribute, defaultValue);
					
					retVals.add(value);
					
					return retVals;
				}
				
				else if(schema.getAttribute(attribute).isRequired()) {
					
					throw new RequiredAttributeMissingException();
				}
				
				return null;
				
			}
			
			else {
				return values;
			}
		}
	}

	public Attribute getSingleAttribute(String attribute) {
		
		// if the requested attribute doesn't exist, throw
		// an AttributeNotDefinedException
		Set<Attribute> values = entity.getFullAttribute(attribute);
		
		if(schema == null) {

			throw new MissingSchemaException();
		}
		
		else if(schema.getAttribute(attribute) == null) {
			
			throw new AttributeNotDefinedException();
		}
		
		else {
			if(values == null) {
				
				String defaultValue = schema.getDefaultValueForAttribute(attribute);
				
				if(defaultValue != null) {
					
					Attribute value = new Attribute(attribute, defaultValue);
					
					return value;
				}
				
				else if(schema.getAttribute(attribute).isRequired()) {
					
					throw new RequiredAttributeMissingException();
				}
				
				return null;
			}
			
			else {
				return (Attribute) values.toArray()[0];
			}
		}
	}

	public Set<Relationship> getFullRelationship(String relationship) {

		
		Set<Relationship> values = entity.getFullRelationship(relationship);
		
		if(schema == null) {

			throw new MissingSchemaException();
		}
		
		else if(schema.getRelationship(relationship) == null) {
			
			throw new AttributeNotDefinedException();
		}
		
		else {
			if(values == null && 
					schema.getRelationship(relationship).isRequired()) {
				
				throw new RequiredAttributeMissingException();
			}
			
			else {
				return values;
			}
		}
	}

	public Relationship getSingleRelationship(String relationship) {

		Set<Relationship> values = entity.getFullRelationship(relationship);
		
		if(schema == null) {

			throw new MissingSchemaException();
		}
		
		else if(schema.getAttribute(relationship) == null) {
			
			throw new AttributeNotDefinedException();
		}
		
		else {
			if(values == null && schema.getRelationship(relationship).isRequired()) {
				
				throw new RequiredAttributeMissingException();
			}
			
			else {
				return (Relationship) values.toArray()[0];
			}
		}	
	}

	public void addAttribute(Attribute attribute) {
		
		if(schema.getAttribute(attribute.getKey()) == null) {
			
			throw new AttributeNotDefinedException();
		}
		
		else {
			entity.addAttribute(attribute);
		}
	}

	public void addRelationship(Relationship relationship) {
		
		if(schema.getRelationship(relationship.getKey()) == null) {
			
			throw new AttributeNotDefinedException();
		}
		
		else {
			entity.addRelationship(relationship);
		}
	}
	
	public boolean validate() {

		if(schema == null) {
			
			return false;
		}
		
		else {
			
			return validator.validate();

		}
		
//		// does each attribute exist in the schema?
//		for(String attribute : getAttributeKeySet()) {
//			if(schema.getAttribute(attribute) == null ||
//					!schema.getAttribute(attribute)
//						.getType().equals(AttributeType.attribute)) {
//				
//				return false;
//			}
//		}
//
//		// does each relationship exist in the schema?
//		for(String relationship : getRelationshipKeySet()) {
//			if(schema.getAttribute(relationship) == null ||
//					!schema.getAttribute(relationship)
//						.getType().equals(AttributeType.relationship)) {
//				
//				return false;
//			}
//		}
//		
//		// now we need to test if each attribute passes the regex
//		
//		
//		// and test if each relationship passes the regex
//		
//		
//		// test to make sure all required fields are satisfied

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
}
