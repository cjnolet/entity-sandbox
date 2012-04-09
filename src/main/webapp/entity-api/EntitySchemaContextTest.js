test('schema context initialized', function() {
	
	var schemas = {
		
		activityGroup: {
			
			attributes: [{
				name:'hello',
				required:true
			}]
		}
	}
	
	var context = new entity.api.EntitySchemaContext(schemas);
    
    ok(context != null, 'Context schemas initialized ok');
    equals(context.schemas, schemas, 'schemas ok');
});

test("required attributes returned", function() {
	
	var schemas = {
		
		activityGroup: {
			
			attributes: [{
				name:'hello',
				required:true
			},
			{
				name:'goodbye',
				required:true
				
			},
			{
				name:'test',
				required:false
			},
			{
				name:'test2'
			}]
		}
	}
	
	var context = new entity.api.EntitySchemaContext(schemas);
	
	ok(context instanceof entity.api.EntitySchemaContext, 'context is correct type');
	var required = context.getRequiredAttributes('activityGroup');
	
	ok(required != null, "Required attributes returned ok");
	equals(required[0], schemas.activityGroup.attributes[0], 'required attributes ok');
	equals(required[1], schemas.activityGroup.attributes[1], 'required attributes ok');
	equals(2, required.length, 'required attributes correct size');
});

test('validate schema attribute not defined', function() {

	var schemas = {
			
		activityGroup: {
			
			allowsUndefinedAttributes:false
		}
	}
	
	var context = new entity.api.EntitySchemaContext(schemas);
	
	var theEntity = new Entity('id', 'activityGroup');
	var testEntity = new EntityBuilder(theEntity).attr('testKey', 'testValue').create();

	var validate = context.validate(testEntity);
	ok(validate != null, 'context did not validate');
    ok(validate['testKey'] != null, 'returned validator has record for key');
    equals(1, validate['testKey'].length)
    equals('Attribute not defined.', validate['testKey'][0])
	
});

test('validate schema attribute singleton error', function() {

	var schemas = {
			
		activityGroup: {
			
			allowsUndefinedAttributes:false,
			attributes: [{
				
				name:'hello',
				hasMany:false
			}]
		}
	}
	
	var context = new entity.api.EntitySchemaContext(schemas);
	
	var theEntity = new Entity('id', 'activityGroup');
	var testEntity = new EntityBuilder(theEntity).attr('hello', 'val')
		.attr('hello', 'value2')
		.create();

	var validate = context.validate(testEntity);
	ok(validate != null, 'context did not validate');
    ok(validate['hello'] != null, 'returned validator has record for key');
    equals(1, validate['hello'].length)
    equals('Singleton violation', validate['hello'][0])
});

test('validate schema attribute required error', function() {

	var schemas = {
			
		activityGroup: {
			
			allowsUndefinedAttributes:false,
			attributes: [{
				
				name:'hello',
				required:true
			}]
		}
	}
	
	var context = new entity.api.EntitySchemaContext(schemas);
	
	var theEntity = new Entity('id', 'activityGroup');
	var testEntity = new EntityBuilder(theEntity).create();

	var validate = context.validate(testEntity);
	ok(validate != null, 'context did not validate');
    ok(validate['hello'] != null, 'returned validator has record for key');
    equals(1, validate['hello'].length)
    equals('Missing required', validate['hello'][0])
});

test('validate schema attribute regex success', function() {

	var schemas = {
			
		activityGroup: {
			
			allowsUndefinedAttributes:false,
			attributes: [{
				
				name:'hello',
				hasMany:false,
				validationRegex:"\\d+-\\d"
			}]
		}
	}
	
	var context = new entity.api.EntitySchemaContext(schemas);
	
	var theEntity = new Entity('id', 'activityGroup');
	var testEntity = new EntityBuilder(theEntity).attr('hello', '44444-6').create();

	var validate = context.validate(testEntity);
	ok(validate == null, 'context validated ok');
});

test('validate schema attribute regex error', function() {

	var schemas = {
			
		activityGroup: {
			
			allowsUndefinedAttributes:false,
			attributes: [{
				
				name:'hello',
				hasMany:false,
				validationRegex:"\\d+-\\d"
			}]
		}
	}
	
	var context = new entity.api.EntitySchemaContext(schemas);
	
	var theEntity = new Entity('id', 'activityGroup');
	var testEntity = new EntityBuilder(theEntity).attr('hello', '44444-d').create();

	var validate = context.validate(testEntity);
	ok(validate != null, 'context did not validate');
    ok(validate['hello'] != null, 'returned validator has record for key');
    equals(1, validate['hello'].length)
    equals('Failed regex', validate['hello'][0])	
});

test('validate schema relationship not defined', function() {

	var schemas = {
			
		activityGroup: {
			
			allowsUndefinedRelationships:false
		}
	}
	
	var context = new entity.api.EntitySchemaContext(schemas);
	
	var theEntity = new Entity('id', 'activityGroup');
	var testEntity = new EntityBuilder(theEntity).rel('testKey', 'testValue').create();

	var validate = context.validate(testEntity);
	ok(validate != null, 'context did not validate');
    ok(validate['testKey'] != null, 'returned validator has record for key');
    equals(1, validate['testKey'].length)
    equals('Relationship not defined', validate['testKey'][0])
	
});



