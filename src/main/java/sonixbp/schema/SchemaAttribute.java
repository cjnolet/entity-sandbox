package sonixbp.schema;


public class SchemaAttribute {
	
	String name;
	String defaultValue;
	
	String validationRegex;
	
	public String toString() {
		return "[name=" + name + ", defaultValue=" + defaultValue + ", validationRegex=" + validationRegex + "]";
	}
}
