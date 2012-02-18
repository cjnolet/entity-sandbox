package sonixbp.schema;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EntitySchemaTest {
	
	
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testParseEntityFromResource() {
		
		EntitySchema schema = EntitySchema.parseEntitySchemaFromResource("schema.json", "activityGroup");
		
		assertNotNull(schema);
	}
	
}
