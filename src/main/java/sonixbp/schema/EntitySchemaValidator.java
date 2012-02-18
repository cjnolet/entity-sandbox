package sonixbp.schema;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sonixbp.domain.Attribute;
import sonixbp.domain.BasicEntity;
import sonixbp.domain.Tuple;
import sonixbp.domain.Relationship;


public class EntitySchemaValidator  {


	private Map<Tuple, Set<ValidationErrorType>> validationErrors;
	
	EntitySchema schema;
	BasicEntity entity;
	
	public EntitySchemaValidator(EntitySchema schema, BasicEntity entity) {
		this.schema = schema;
		this.entity = entity;
	}
	
	public Map<Tuple, Set<ValidationErrorType>> getErrors() {
		return validationErrors;
	}

	public boolean validate() {
		
		validationErrors = new HashMap<Tuple, Set<ValidationErrorType>>();
		
		for(String attributeName : entity.getAttributeKeySet()) {
			
			Set<Attribute> attributeValues = entity.getFullAttribute(attributeName);
			SchemaAttribute schemaAttr = schema.getAttribute(attributeName);
			
			// if the schema is not allowing undefined attributes & one is present, fail
			if(schemaAttr == null && !schema.allowsUndefinedAttributes() && 
					(attributeValues != null && attributeValues.size() > 0)) {
				
				for(Tuple value : attributeValues) {
					addError(value, ValidationErrorType.NOT_DEFINED);
				}
			}
			
			// if we have a missing required field and not default value set
			if(schemaAttr != null && schemaAttr.isRequired() && 
					attributeValues == null && schemaAttr.getDefaultValue() == null) {
				
				addError(new Tuple(attributeName, null), ValidationErrorType.MISSING_REQUIRED);
			}
			
			// if we have many values for a schema where hasMany = false
			if(schemaAttr != null && !schemaAttr.hasMany() &&
					(attributeValues != null && attributeValues.size() > 1)) {
				
				for(Tuple value : attributeValues) {
					addError(value, ValidationErrorType.SINGLETON_VIOLATION);
				}
			}
			
			// if the schema has a regex, make sure all values pass
			if(schemaAttr != null && schemaAttr.getValidationRegex() != null) {
				
				for(Tuple value : attributeValues) {
					
					Pattern p = Pattern.compile(schemaAttr.getValidationRegex());
					Matcher m = p.matcher((String)value.getValue());
					
					if(!m.matches()) {
						addError(value, ValidationErrorType.REGEX_FAILED);
					}
				}
			}
		}
		
		for(String relationshipName : entity.getRelationshipKeySet()) {
			
			Set<Relationship> relationshipValues = entity.getFullRelationship(relationshipName);
			SchemaRelationship schemaRel = schema.getRelationship(relationshipName);
			
			// if the schema is not allowing undefined relationships & one is present, fail
			if(schemaRel == null && !schema.allowsUndefinedRelationships() && 
					(relationshipValues != null && relationshipValues.size() > 0)) {
				
				for(Tuple value : relationshipValues) {
					addError(value, ValidationErrorType.NOT_DEFINED);
				}
			}
			
			// if we have a missing required field and not default value set
			if(schemaRel != null && schemaRel.isRequired() && 
					relationshipValues == null) {
				
				addError(new Tuple(relationshipName, null), ValidationErrorType.MISSING_REQUIRED);
			}
			
			// if we have many values for a schema where hasMany = false
			if(schemaRel != null && !schemaRel.hasMany() &&
					(relationshipValues != null && relationshipValues.size() > 1)) {
				
				for(Tuple value : relationshipValues) {
					addError(value, ValidationErrorType.SINGLETON_VIOLATION);
				}
			}
			
			// if one of the relationship values is not of the correct type
			if(schemaRel != null && schemaRel.getEntityTypeConstraint() != null) {
				
				for(Relationship value : relationshipValues) {
					
					if(!value.getType().equals(schemaRel.getEntityTypeConstraint())) {
						
						addError(value, ValidationErrorType.INCORRECT_TYPE);
					}
				}
			}
		}
		
		if(schema.getAttributes() != null) {
			for(SchemaAttribute attribute : schema.getAttributes()) {
				
				if(entity.getFullAttribute(attribute.getName()) == null &&
						attribute.isRequired()) {
					
					addError(new Tuple(attribute.getName(), 
							null), ValidationErrorType.MISSING_REQUIRED);
					
				}
			}
		}

		return validationErrors.size() == 0;
	}
	
	private void addError(Tuple value, ValidationErrorType errorType) {
		
		if(validationErrors.get(value) == null) {
			
			Set<ValidationErrorType> errors = new HashSet<ValidationErrorType>();
			errors.add(errorType);
			
			validationErrors.put(value, errors);
		}
		
		else {
			
			Set<ValidationErrorType> errors = validationErrors.get(value);
			errors.add(errorType);
		}
	}
}
