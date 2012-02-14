package sonixbp.schema;

import java.util.Set;

import sonixbp.listener.AttributeListener;


public class SchemaAttribute {

	private String name;
	private String defaultValue;
	private String validationRegex;
	private boolean required = false;
	
	private AttributeListener attributeListener;
	
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
