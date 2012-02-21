package sonixbp.exception;

public class MalformedSchemaException extends EntitySchemaException {
	
	private static final long serialVersionUID = 1L;
	String location;
	
	public MalformedSchemaException(String location) {
		this.location = location;
	}
	
	@Override
	public String getMessage() {
		return "The schema file " + location + " is malformed.";
	}
}
