package sonixbp.schema;

import java.util.Map;
import java.util.Set;

import sonixbp.domain.BasicEntity;
import sonixbp.domain.EValue;


public class EntitySchemaValidator  {


	private Map<EValue, Set<String>> validationErrors;
	
	EntitySchema schema;
	BasicEntity entity;
	
	public EntitySchemaValidator(EntitySchema schema, BasicEntity entity) {
		this.schema = schema;
		this.entity = entity;
	}
	
	public Map<EValue, String> getErrors() {
		return validationErrors;
	}

	public boolean validate() {
		
		// first check that the attribute exists in the schema
		
		// check to make sure the attribute is of the correct type
		
		// check to make sure the attribute passes regex if one is present
		
		// check to make sure any required attributes are present
		
		return false;
	}
}
