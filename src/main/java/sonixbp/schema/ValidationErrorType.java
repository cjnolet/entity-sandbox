package sonixbp.schema;

public enum ValidationErrorType {
	
	NOT_DEFINED("Item not defined in the schema for type"), 
	MISSING_REQUIRED("Required field missing"), 
	REGEX_FAILED("Value did not pass regex"), 
	INCORRECT_TYPE("Type mismatch in relationship"),
	SINGLETON_VIOLATION("Multiple values found for singleton field");
	
	private String validationString;
	ValidationErrorType(String validationString) {
		this.validationString = validationString;
	}
	
	public String toString() {
		return this.validationString;
	}
}
