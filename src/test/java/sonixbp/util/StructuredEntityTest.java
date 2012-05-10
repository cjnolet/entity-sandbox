package sonixbp.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sonixbp.domain.BasicEntity;
import sonixbp.domain.Entity;
import sonixbp.domain.StructuredEntity;
import sonixbp.exception.MissingSchemaException;

public class StructuredEntityTest {

	@Before
	public void SetUp() {
		
	}
	
	@Test
	public void testEntityWithMissingSchema() {
		
		BasicEntity entity = new Entity("noschema", "id");
			
		try {
			StructuredEntity sEntity = new StructuredEntity("noschema", entity);
			fail();
		} catch(MissingSchemaException e) {
			
			System.out.println("FAILED!");
		}
	}
	
//	@Test
//	public void testEntitWithAssociatedSchema() {
//		BasicEntity entity = new Entity("activityGroup", "id");
//
//		try {
//			StructuredEntity sEntity = new StructuredEntity("noschema", entity);
//		}
//
//		catch(MissingSchemaException e) {
//			fail();
//		}
//	}
}
