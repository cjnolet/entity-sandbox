package sonixbp.schema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import sonixbp.domain.Attribute;
import sonixbp.domain.Tuple;
import sonixbp.domain.Entity;
import sonixbp.domain.Relationship;

public class EntitySchemaValidatorTest {

	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testValidatePasses() {
		
		EntitySchema schema = EntitySchema.parseEntitySchemaFromResource("schema.json", "activityGroup");
		
		Entity entity = new Entity("TEST", "TEST");
		entity.addAttribute(new Attribute("ruleIds", "TEST"));

		EntitySchemaValidator validator = new EntitySchemaValidator(schema, entity);
		
		assertTrue(validator.validate());
		assertEquals(0, validator.getErrors().size());
	}
	
	@Test
	public void testValidationFailedMissingRequired() {
		
		EntitySchema schema = EntitySchema.parseEntitySchemaFromResource("schema.json", "activityGroup");
		
		Entity entity = new Entity("TEST", "TEST");

		EntitySchemaValidator validator = new EntitySchemaValidator(schema, entity);
		
		assertFalse(validator.validate());
		assertEquals(1, validator.getErrors().size());
		
		for(Entry<Tuple, Set<ValidationErrorType>> entry : validator.getErrors().entrySet()) {
			
			assertEquals(1, entry.getValue().size());
			
			for(ValidationErrorType error : entry.getValue()) {
				assertEquals(ValidationErrorType.MISSING_REQUIRED, error);
			}
		}
	}

	
	@Test
	public void testValidationFailedUndefinedAttribute() {
		
		EntitySchema schema = EntitySchema.parseEntitySchemaFromResource("schema.json", "activityGroup");
		
		Entity entity = new Entity("TEST", "TEST");
		entity.addAttribute(new Attribute("ruleIds", "TEST"));
		entity.addAttribute(new Attribute("ruleId1", "TEST"));

		EntitySchemaValidator validator = new EntitySchemaValidator(schema, entity);
		
		assertFalse(validator.validate());
		assertEquals(1, validator.getErrors().size());
		
		for(Entry<Tuple, Set<ValidationErrorType>> entry : validator.getErrors().entrySet()) {
			
			assertEquals(1, entry.getValue().size());
			
			assertEquals("ruleId1", entry.getKey().getKey());
			assertEquals("TEST", entry.getKey().getValue());
			
			for(ValidationErrorType error : entry.getValue()) {
				assertEquals(ValidationErrorType.NOT_DEFINED, error);
			}
		}
	}
	
	@Test
	public void testValidationFailedUndefinedRelationship() {
		
		EntitySchema schema = EntitySchema.parseEntitySchemaFromResource("schema.json", "activityGroup");
		
		Entity entity = new Entity("TEST", "TEST");
		entity.addAttribute(new Attribute("ruleIds", "TEST"));
		entity.addRelationship(new Relationship("ruleId", "TEST", "alertingRule"));

		EntitySchemaValidator validator = new EntitySchemaValidator(schema, entity);
		
		assertFalse(validator.validate());
		assertEquals(1, validator.getErrors().size());
		
		for(Entry<Tuple, Set<ValidationErrorType>> entry : validator.getErrors().entrySet()) {
			
			assertEquals(1, entry.getValue().size());
			
			assertEquals("ruleId", entry.getKey().getKey());
			assertEquals("TEST", entry.getKey().getValue());
			
			for(ValidationErrorType error : entry.getValue()) {
				assertEquals(ValidationErrorType.NOT_DEFINED, error);
			}
		}
	}
	
	@Test
	public void testValidateEmptySchemaAttributeNotDefined() {
		
		EntitySchema schema = EntitySchema.parseEntitySchemaFromResource("schema.json", "empty");
		
		Entity entity = new Entity("TEST", "TEST");
		entity.addAttribute(new Attribute("ruleIds", "TEST"));

		EntitySchemaValidator validator = new EntitySchemaValidator(schema, entity);
		
		assertFalse(validator.validate());
		assertEquals(1, validator.getErrors().size());
		
		for(Entry<Tuple, Set<ValidationErrorType>> entry : validator.getErrors().entrySet()) {
			
			assertEquals(1, entry.getValue().size());
			
			assertEquals("ruleIds", entry.getKey().getKey());
			assertEquals("TEST", entry.getKey().getValue());
			
			for(ValidationErrorType error : entry.getValue()) {
				assertEquals(ValidationErrorType.NOT_DEFINED, error);
			}
		}
	}

	@Test
	public void testValidateEmptySchemaRelationshipNotDefined() {
		
		EntitySchema schema = EntitySchema.parseEntitySchemaFromResource("schema.json", "empty");
		
		Entity entity = new Entity("TEST", "TEST");
		entity.addRelationship(new Relationship("ruleIds", "TEST", "type"));

		EntitySchemaValidator validator = new EntitySchemaValidator(schema, entity);
		
		assertFalse(validator.validate());
		assertEquals(1, validator.getErrors().size());
		
		for(Entry<Tuple, Set<ValidationErrorType>> entry : validator.getErrors().entrySet()) {
			
			assertEquals(1, entry.getValue().size());
			
			assertEquals("ruleIds", entry.getKey().getKey());
			assertEquals("TEST", entry.getKey().getValue());
			
			for(ValidationErrorType error : entry.getValue()) {
				assertEquals(ValidationErrorType.NOT_DEFINED, error);
			}
		}
	}

	@Test
	public void testValidateEmptySchemaRelationshipsAllowed() {
		
		EntitySchema schema = EntitySchema.parseEntitySchemaFromResource("schema.json", "relationshipsAllowed");
		
		Entity entity = new Entity("TEST", "TEST");
		entity.addRelationship(new Relationship("ruleIds", "TEST", "type"));

		EntitySchemaValidator validator = new EntitySchemaValidator(schema, entity);
		
		assertTrue(validator.validate());
		assertEquals(0, validator.getErrors().size());
		
	}

	@Test
	public void testValidateEmptySchemaAttributessAllowed() {
		
		EntitySchema schema = EntitySchema.parseEntitySchemaFromResource("schema.json", "attributesAllowed");
		
		Entity entity = new Entity("TEST", "TEST");
		entity.addAttribute(new Attribute("ruleIds", "TEST"));

		EntitySchemaValidator validator = new EntitySchemaValidator(schema, entity);
		
		assertTrue(validator.validate());
		assertEquals(0, validator.getErrors().size());
		
	}


	@Test
	public void testValidateAttributeNotRequired() {
		
		EntitySchema schema = EntitySchema.parseEntitySchemaFromResource("schema.json", "attributeNotRequired");
		
		Entity entity = new Entity("TEST", "TEST");

		EntitySchemaValidator validator = new EntitySchemaValidator(schema, entity);
		
		assertTrue(validator.validate());
		assertEquals(0, validator.getErrors().size());
		
	}
	
	@Test
	public void testValidateRelationshipCorrectType() {
		
		EntitySchema schema = EntitySchema.parseEntitySchemaFromResource("schema.json", "relationshipTypeConstraint");
		
		Entity entity = new Entity("TEST", "TEST");
		entity.addRelationship(new Relationship("ruleIds", "TEST", "alertingRule"));

		EntitySchemaValidator validator = new EntitySchemaValidator(schema, entity);
		
		assertTrue(validator.validate());
		assertEquals(0, validator.getErrors().size());
		
	}
	
	@Test
	public void testValidateRelationshipIncorrectType() {
		
		EntitySchema schema = EntitySchema.parseEntitySchemaFromResource("schema.json", "relationshipTypeConstraint");
		
		Entity entity = new Entity("TEST", "TEST");
		entity.addRelationship(new Relationship("ruleIds", "TEST", "alertingRuleInvalid"));

		EntitySchemaValidator validator = new EntitySchemaValidator(schema, entity);
		
		assertFalse(validator.validate());
		assertEquals(1, validator.getErrors().size());
		
		
		for(Entry<Tuple, Set<ValidationErrorType>> entry : validator.getErrors().entrySet()) {
			
			assertEquals(1, entry.getValue().size());
			
			assertEquals("ruleIds", entry.getKey().getKey());
			assertEquals("TEST", entry.getKey().getValue());
			
			for(ValidationErrorType error : entry.getValue()) {
				assertEquals(ValidationErrorType.INCORRECT_TYPE, error);
			}
		}
	}
	
	@Test
	public void testValidateAttributeHasManyViolation() {

		EntitySchema schema = EntitySchema.parseEntitySchemaFromResource("schema.json", "singleton");
		
		Entity entity = new Entity("TEST", "TEST");
		entity.addAttribute(new Attribute("ruleIds", "TEST"));
		entity.addAttribute(new Attribute("ruleIds", "TEST2"));

		EntitySchemaValidator validator = new EntitySchemaValidator(schema, entity);
		
		assertFalse(validator.validate());
		assertEquals(2, validator.getErrors().size());
		
		for(Entry<Tuple, Set<ValidationErrorType>> entry : validator.getErrors().entrySet()) {
			
			assertEquals(1, entry.getValue().size());

			for(ValidationErrorType error : entry.getValue()) {
				assertEquals(ValidationErrorType.SINGLETON_VIOLATION, error);
			}
		}
	}
	
	@Test
	public void testValidateAttributeHasManyCorrect() {

		EntitySchema schema = EntitySchema.parseEntitySchemaFromResource("schema.json", "singleton");
		
		Entity entity = new Entity("TEST", "TEST");
		entity.addAttribute(new Attribute("ruleIds", "TEST"));
		entity.addAttribute(new Attribute("ruleIds2", "TEST"));
		entity.addAttribute(new Attribute("ruleIds2", "TEST1"));

		EntitySchemaValidator validator = new EntitySchemaValidator(schema, entity);
		
		assertTrue(validator.validate());
		assertEquals(0, validator.getErrors().size());
	}
	
	@Test
	public void testValidateRelationshipHasManyViolation() {

		EntitySchema schema = EntitySchema.parseEntitySchemaFromResource("schema.json", "singleton");
		
		Entity entity = new Entity("TEST", "TEST");
		entity.addAttribute(new Attribute("ruleIds", "TEST"));
		entity.addRelationship(new Relationship("rel1", "TEST", "testType"));
		entity.addRelationship(new Relationship("rel1", "TEST2", "testType"));

		EntitySchemaValidator validator = new EntitySchemaValidator(schema, entity);

		assertFalse(validator.validate());
		assertEquals(2, validator.getErrors().size());
		
		for(Entry<Tuple, Set<ValidationErrorType>> entry : validator.getErrors().entrySet()) {
			
			assertEquals(1, entry.getValue().size());

			for(ValidationErrorType error : entry.getValue()) {
				assertEquals(ValidationErrorType.SINGLETON_VIOLATION, error);
			}
		}
	}
	
	@Test
	public void testValidateRelationshipHasManyCorrect() {

		EntitySchema schema = EntitySchema.parseEntitySchemaFromResource("schema.json", "singleton");
		
		Entity entity = new Entity("TEST", "TEST");
		entity.addAttribute(new Attribute("ruleIds", "TEST"));
		entity.addRelationship(new Relationship("rel1", "TEST", "testType"));

		EntitySchemaValidator validator = new EntitySchemaValidator(schema, entity);

		assertTrue(validator.validate());
		assertEquals(0, validator.getErrors().size());
	}
	
}
