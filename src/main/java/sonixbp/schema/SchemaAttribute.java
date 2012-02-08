package sonixbp.schema;


public class SchemaAttribute {

	String name;
	String defaultValue;
	
	String validationRegex;
	
	AttributeType type;
	
	public String getName() {
		return name;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public String getValidationRegex() {
		return validationRegex;
	}

	public AttributeType getType() {
		return type;
	}
	
	public String toString() {
		return "[name=" + name + ", type=" + type + ", defaultValue=" + defaultValue + 
		", validationRegex=" + validationRegex + "]";
	}
}
