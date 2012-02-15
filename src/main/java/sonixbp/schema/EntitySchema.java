package sonixbp.schema;

import java.util.List;

public class EntitySchema {
	
	private boolean allowsUndefinedAttributes = false;
	private List<SchemaAttribute> attributes;
	private List<SchemaRelationship> relationships;

	public List<SchemaAttribute> getAttributes() {
		return attributes;
	}

	public List<SchemaRelationship> getRelationships() {
		return relationships;
	}

	public String toString() {
		return "attributes= [" + attributes != null ? attributes.toString() : "" + "], " + 
			   "relationships = [" + relationships != null ? relationships.toString() : "" + "]";
	}
	
	public boolean allowsUndefinedAttributes() {
		return allowsUndefinedAttributes;
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
	
	public SchemaRelationship getRelationship(String relationship) {

		for(SchemaRelationship sRel : relationships) {
			if(sRel.getName().equals(relationship)) {
				return sRel;
			}
		}
		
		return null;
	}
}
