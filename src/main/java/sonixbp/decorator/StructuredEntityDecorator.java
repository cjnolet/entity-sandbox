package sonixbp.decorator;

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
}
