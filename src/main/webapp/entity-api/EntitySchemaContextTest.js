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
	
	var required = context.getRequiredAttributes('activityGroup');
	
	ok(required != null, "Required attributes returned ok");
	equals(required[0], schemas.activityGroup.attributes[0], 'required attributes ok');
	equals(required[1], schemas.activityGroup.attributes[1], 'required attributes ok');
	equals(2, required.length, 'required attributes correct size');
});
