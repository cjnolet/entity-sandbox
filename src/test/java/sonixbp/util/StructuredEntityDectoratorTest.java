package sonixbp.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sonixbp.decorator.StructuredEntityDecorator;
import sonixbp.domain.Entity;
import sonixbp.domain.EntityImpl;
import sonixbp.exception.MissingSchemaException;

public class StructuredEntityDectoratorTest {

	@Before
	public void SetUp() {
		
	}
	
	@Test
	public void testEntityWithMissingSchema() {
		
		Entity entity = new EntityImpl("noschema", "id");
			
		try {
			StructuredEntityDecorator sEntity = new StructuredEntityDecorator(entity);
			fail();
		} catch(MissingSchemaException e) {
			
			System.out.println("FAILED!");
		}
	}
	
	@Test
	public void testEntitWithAssociatedSchema() {
		Entity entity = new EntityImpl("activityGroup", "id");
		
		try {
			StructuredEntityDecorator sEntity = new StructuredEntityDecorator(entity);
		}
		
		catch(MissingSchemaException e) {
			fail();
		}
	}
}
