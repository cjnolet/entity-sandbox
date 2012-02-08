package sonixbp.schema;

import java.util.Map;
import java.util.Set;

import sonixbp.domain.EValue;

public class SchemaValidator {
	
	public static final String FAILS_REQUIRED = "Required field missing";
	public static final String FAILS_REGEX = "The value does not pass the validator regex";
	
	boolean passed;
	EntitySchema schema;
	
	public SchemaValidator(EntitySchema schema) {
		this.schema = schema;
	}
	
	private Map<String, String> validationResult;
	
	
	public void validateAttributes(Set<EValue> values, AttributeType type) {
		
		// first check that the attribute exists in the schema
		
		// check to make sure the attribute is of the correct type
		
		// check to make sure the attribute passes regex if one is present
		
		// check to make sure any required attributes are present
	}
	
	
	
	public Map<String,String> getValidationErrors() {
		return validationResult;
	}
}
