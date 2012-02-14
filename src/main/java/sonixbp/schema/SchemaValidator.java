package sonixbp.schema;

import java.util.Map;

import sonixbp.domain.BasicEntity;

public class SchemaValidator {
	
	public static final String FAILS_REQUIRED = "Required field missing";
	public static final String FAILS_REGEX = "The value does not pass the validator regex";

	private Map<String, String> validationErrors;
	
	boolean passed;

	EntitySchema schema;
	BasicEntity entity;
	
	public SchemaValidator(EntitySchema schema, BasicEntity entity) {
		this.schema = schema;
		this.entity = entity;
	}

	public boolean validate() {
		
		// first check that the attribute exists in the schema
		
		// check to make sure the attribute is of the correct type
		
		// check to make sure the attribute passes regex if one is present
		
		// check to make sure any required attributes are present
		
		return false;
	}
	
	public boolean validationPassed() {
		return passed;
	}
	
	
	public Map<String,String> getValidationErrors() {
		return validationErrors;
	}
}
