package sonixbp.util;

import static org.junit.Assert.*;

import org.junit.Test;

import sonixbp.schema.EntitySchema;
import sonixbp.schema.SchemaAttribute;

public class EntitySchemaUtilsTest {

	@Test
	public void testParseEntitySchemaFromResource() {
		
		EntitySchema schema = EntitySchemaUtils.parseEntitySchemaFromResource("activityGroup");
		
		System.out.println("SCHEMA=" + schema);
		
		int count = 0;
		for(SchemaAttribute attribute : schema.getAttributes()) {
			count++;
			
			if(count == 1) {
				assertEquals("ruleIds", attribute.getName());
			}
		}
	}
}
