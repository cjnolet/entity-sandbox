package sonixbp.domain;

import sonixbp.datatype.type.URIType;

public class Relationship extends Attribute<URIType> {
	
	public Relationship(String key, URIType value, String type) {
		super(key, value);
		this.type = type;
	}
	
	private String type;
	
	public String getType() {
		return type;
		
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
