package sonixbp.util;

import org.junit.Test;

import sonixbp.schema.EntitySchema;

public class EntitySchemaUtilsTest {

	
	@Test
	public void testParseEntitySchemaFromResource() {
		
		EntitySchema schema = EntitySchemaUtils.parseEntitySchemaFromResource("activityGroup");
		
		System.out.println("SCHEMA=" + schema);
	}
}
