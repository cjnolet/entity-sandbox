package sonixbp.schema;

public class SchemaRelationship {

	private String name;
	private String entityTypeConstraint;
	
	private boolean hasMany = false;
	private boolean required = false;

	public String getName() {
		return name;
	}

	public String getEntityTypeConstraint() {
		return entityTypeConstraint;
	}

	public boolean hasMany() {
		return hasMany;
	}

	public boolean isRequired() {
		return required;
	}
}
