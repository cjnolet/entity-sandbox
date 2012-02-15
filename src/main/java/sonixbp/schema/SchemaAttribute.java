package sonixbp.schema;

import java.util.Set;

import sonixbp.listener.AttributeListener;


public class SchemaAttribute {

	private String name;
	private String defaultValue;
	private String validationRegex;

	private boolean required = false;
	private boolean hasMany = false;

	
	public boolean isRequired() {
		return required;
	}

	public boolean hasMany() {
		return hasMany;
	}
	
	public String getName() {
		return name;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public String getValidationRegex() {
		return validationRegex;
	}
	
	public String toString() {
		return "[name=" + name + ", " + "required=" + required + 
		", defaultValue=" + defaultValue + 
		", validationRegex=" + validationRegex + "]";
	}
}
