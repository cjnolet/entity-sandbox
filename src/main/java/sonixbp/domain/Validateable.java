package sonixbp.domain;

import java.util.Map;
import java.util.Set;

import sonixbp.schema.ValidationErrorType;

public interface Validateable {
	
	boolean validate();
	
	Map<Tuple, Set<ValidationErrorType>> getErrors();

}
