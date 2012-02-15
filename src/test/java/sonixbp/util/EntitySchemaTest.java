package sonixbp.util;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import sonixbp.exception.MissingSchemaException;
import sonixbp.schema.EntitySchema;
import sonixbp.schema.SchemaAttribute;

public class EntitySchemaTest {

	@Test
	public void testParseEntitySchemaFromResource() {
		
		EntitySchema schema = EntitySchema.parseEntitySchemaFromResource("schema.json", "activityGroup");
		
		System.out.println("SCHEMA=" + schema);
		
		int count = 0;
		for(SchemaAttribute attribute : schema.getAttributes()) {
			count++;
			
			if(count == 1) {
				assertEquals("ruleIds", attribute.getName());
			}
		}
	}
	
	@Test
	public void testMissingSchemaThrowsException() {
		
		try {

			EntitySchema schema = EntitySchema.parseEntitySchemaFromResource("schema.json", "missing");
			fail("A MissingSchemaException should have been thrown");

		} catch(MissingSchemaException e) {}
	}
	
	@Test 
	public void testMissingSchemaFileThrowsException() {
		try {
			EntitySchema schema = EntitySchema.parseEntitySchemaFromResource("missingFile", "activityGroup");
			fail("A MissingSchemaException should have been thrown");
		}
		
		catch(MissingSchemaException e) {}
	}
}
