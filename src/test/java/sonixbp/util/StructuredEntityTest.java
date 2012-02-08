package sonixbp.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sonixbp.domain.Entity;
import sonixbp.domain.EntityImpl;
import sonixbp.domain.StructuredEntity;
import sonixbp.exception.MissingSchemaException;

public class StructuredEntityTest {

	@Before
	public void SetUp() {
		
	}
	
	@Test
	public void testEntityWithMissingSchema() {
		
		Entity entity = new EntityImpl("noschema", "id");
			
		try {
			StructuredEntity sEntity = new StructuredEntity(entity);
			fail();
		} catch(MissingSchemaException e) {
			
			System.out.println("FAILED!");
		}
	}
	
	@Test
	public void testEntitWithAssociatedSchema() {
		Entity entity = new EntityImpl("activityGroup", "id");
		
		try {
			StructuredEntity sEntity = new StructuredEntity(entity);
		}
		
		catch(MissingSchemaException e) {
			fail();
		}
	}
}
