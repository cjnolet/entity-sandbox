package sonixbp.decorator;

import java.util.Set;

import sonixbp.domain.EValue;
import sonixbp.domain.Entity;
import sonixbp.exception.MissingSchemaException;
import sonixbp.schema.EntitySchema;
import sonixbp.util.EntitySchemaUtils;

/**
 * Created by IntelliJ IDEA.
 * User: cnolet
 * Date: Feb 7, 2012
 * Time: 8:44:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class StructuredEntityDecorator implements Entity {

    Entity entity;
    EntitySchema schema;

    public StructuredEntityDecorator(Entity entity) {

        schema = EntitySchemaUtils.parseEntitySchemaFromResource(entity.getType());

        if(schema == null) {
            throw new MissingSchemaException();
        }

        this.entity = entity;
    }

    public String getType() {
        return entity.getType();
    }

	public Set<EValue> getFullAttribute(String attribute) {

		// if the requested attribute doesn't exist, throw
		// an AttributeNotDefinedException

		
		return entity.getFullAttribute(attribute);
	}

	public EValue getSingleAttribute(String attribute) {
		
		// if the requested attribute doesn't exist, throw
		// an AttributeNotDefinedException
		
		return entity.getSingleAttribute(attribute);
	}

	public Set<EValue> getFullRelationship(String relationship) {

		return entity.getFullRelationship(relationship);
	}

	public EValue getSingleRelationship(String relationship) {

		return entity.getSingleRelationship(relationship);
	}

	public void addAttribute(EValue attribute) {
		
		// if the  attribute doesn't validate or is missing...
		// we need to throw  a runtime exception
	}

	public void addRelationship(EValue relationship) {
		
		// if the attribute doesn't validate or is missing...
		// we need to throw a runtime exception`
	}
	
	public boolean validate() {
		// here's where we will use our schema to make sure
		// our attributes fit
		
		if(schema == null) {
			
			return false;
		}
		
		else {
			return true;
		}
	}
}
