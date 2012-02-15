package sonixbp.domain;

import java.util.HashSet;
import java.util.Set;

import sonixbp.exception.AttributeNotDefinedException;
import sonixbp.exception.MissingSchemaException;
import sonixbp.exception.RequiredAttributeMissingException;
import sonixbp.schema.EntitySchema;
import sonixbp.schema.SchemaValidator;
import sonixbp.util.EntitySchemaUtils;

public class StructuredEntity implements BasicEntity, Validateable {

    BasicEntity entity;
    EntitySchema schema;
    SchemaValidator validator;

    public StructuredEntity(BasicEntity entity) {

        schema = EntitySchemaUtils.parseEntitySchemaFromResource(entity.getType());
        this.entity = entity;

        if(schema == null) {
        	
            throw new MissingSchemaException();
        }
        
        else {
            this.validator = new SchemaValidator(schema, entity);
        }
    }

    public String getType() {
        return entity.getType();
    }

	public Set<EValue> getFullAttribute(String attribute) {

		Set<EValue> values = entity.getFullAttribute(attribute);
		
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
					
					Set<EValue> retVals = new HashSet<EValue>();
					
					EValue value = new EValue();
					value.setKey(attribute);
					value.setValue(defaultValue);
					
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

	public EValue getSingleAttribute(String attribute) {
		
		// if the requested attribute doesn't exist, throw
		// an AttributeNotDefinedException
		Set<EValue> values = entity.getFullAttribute(attribute);
		
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
					
					EValue value = new EValue();
					value.setKey(attribute);
					value.setValue(defaultValue);
					
					return value;
				}
				
				else if(schema.getAttribute(attribute).isRequired()) {
					
					throw new RequiredAttributeMissingException();
				}
				
				return null;
			}
			
			else {
				return (EValue) values.toArray()[0];
			}
		}
	}

	public Set<EValue> getFullRelationship(String relationship) {

		
		Set<EValue> values = entity.getFullRelationship(relationship);
		
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

	public EValue getSingleRelationship(String relationship) {

		Set<EValue> values = entity.getFullAttribute(relationship);
		
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
				return (EValue) values.toArray()[0];
			}
		}	
	}

	public void addAttribute(EValue attribute) {
		
		if(schema.getAttribute(attribute.getKey()) == null) {
			
			throw new AttributeNotDefinedException();
		}
		
		else {
			entity.addAttribute(attribute);
		}
	}

	public void addRelationship(EValue relationship) {
		
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
	
	public SchemaValidator getValidator() {
		
		return validator;
	}

	public Set<String> getAttributeKeySet() {
		
		return entity.getAttributeKeySet();
	}

	public Set<String> getRelationshipKeySet() {
		
		return entity.getRelationshipKeySet();
	}

	public void updateAttribute(EValue attribute) {
		
		entity.updateAttribute(attribute);
		
	}

	public void updateRelationship(EValue relationship) {
		
		entity.updateRelationship(relationship);
		
	}

	public void deleteAttribute(EValue attribute) {
		
		entity.deleteAttribute(attribute);
		
	}

	public void deleteRelationship(EValue relationship) {
		entity.deleteRelationship(relationship);
	}
}
