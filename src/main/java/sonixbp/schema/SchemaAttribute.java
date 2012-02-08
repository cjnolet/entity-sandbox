package sonixbp.schema;


public class SchemaAttribute {

	private String name;
	private String defaultValue;
	private String validationRegex;
	private boolean required = false;
	
	private AttributeType type;
	
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
		return "[name=" + name + ", type=" + type + ", required=" + required + 
		", defaultValue=" + defaultValue + 
		", validationRegex=" + validationRegex + "]";
	}
}
