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
	
	public String getDefaultValueForAttribute(String attribute) {
		
		for(SchemaAttribute sAttr : attributes) {
			if(sAttr.getName().equals(attribute)) {
				return sAttr.getDefaultValue();
			}
		}
		
		return null;
	}
	
	public SchemaAttribute getAttribute(String attribute) {

		for(SchemaAttribute sAttr : attributes) {
			if(sAttr.getName().equals(attribute)) {
				return sAttr;
			}
		}
		
		return null;
	}
}
