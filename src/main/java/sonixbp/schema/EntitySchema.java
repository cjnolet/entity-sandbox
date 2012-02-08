package sonixbp.schema;

import java.util.List;

public class EntitySchema {
	
	List<SchemaAttribute> attributes;

	public List<SchemaAttribute> getAttributes() {
		return attributes;
	}

	public String toString() {
		return attributes.toString();
	}

}
