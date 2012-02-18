package sonixbp.domain;

public class Relationship extends Tuple{
	
	public Relationship(String key, Object value, String type) {
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
