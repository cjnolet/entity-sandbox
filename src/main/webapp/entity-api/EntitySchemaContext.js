var entity = entity ? entity : {};
entity.api = entity.api ? entity.api : {};

entity.api.EntitySchemaContext = function(schema) {

	// if the schema is a string, treat it as a URL 
	if(!schema || typeof schemaUrl == 'string') {
		this.schema = schema ? schema : "entity-service/schema.json";

	    var xmlhttp=false;
	    if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
	    	try {
	    		xmlhttp = new XMLHttpRequest();
	    	} catch (e) {
	    		xmlhttp=false;
	    	}
	    }
	    if (!xmlhttp && window.createRequest) {
	    	try {
	    		xmlhttp = window.createRequest();
	    	} catch (e) {
	    		xmlhttp=false;
	    	}
	    }
	    
	    // fetch our schema from the schema location
	    xmlhttp.open("GET", this.schemaUrl, true);

	    xmlhttp.onreadystatechange = function() {

	    	if (xmlhttp.readyState == 4) {
		    	if(xmlhttp.status == 200) {
		    		this.schemas = eval('(' + xmlhttp.responseText + ')');
		    	}
		    	
		    	else {
		    		// handle an exception here?
		    	}
		    }
	    }
	    
	    try {
	        xmlhttp.send(null);
	    }
	    
	    catch(e) {
	    	// do something with caught exception here
	    };
	}
	
	else {
		
		this.schemas = schema;
	}
	
    
    return this;
}

entity.api.EntitySchemaContext.prototype = {
		
	validate: function(entity) {
		
		alert("validate called");
	},
	
	getRequiredAttributes: function(schemaType) {

		if(typeof schemaType === 'string') {
			
			var schema = this.schemas[schemaType];
			
			if(!schema) {
				console.log("Schema for type " + schemaType + " was not found.");
			}
			
			else {
				
				var attributes = schema.attributes;
				var requiredAttributes = [];
				
				if(attributes) {
					
					for(var idx in attributes) {
						
						var schemaAttribute = attributes[idx];

						if(schemaAttribute.required) {
							
							requiredAttributes.push(schemaAttribute);
						}
					}
				}
				
				return requiredAttributes;
			}
		}
		
		else {
			
			throw new Error("Invalid argument");
		}
	},
	
	getRequiredRelationship: function(schemaType) {
		
		if(typeof schemaType === 'string') {
			
			// get all required relationships here
		}
		
		else {
			
			throw new Error("Invalid argument");
		}
	},
	
	getSchemaForType: function(type) {
		
		if(typeof type === 'string') {
			
			if(!this.schemas) {
				console.log("The schema was not found");
				return null;
			}
			
			// return the schema data structure for the specified type
			// (null if it doesn't exist)

			return schemas[type];
		}
		
		else {
			
			throw new Error("Invalid argument");
		}
	}
}
