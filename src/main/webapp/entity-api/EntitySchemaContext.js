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
		
		// first get the schema and make sure something exists
		var schema = this.schemas[entity.type];
		
		var validationResult;
		
		if(!this.schemas) {
			throw new Error("The schema was not found");
		}
		
		var attributes = entity.attributes ? entity.attributes : []
		var relationships = entity.relationships ? entity.relationships : [];

		for(var attributeName in attributes) {
			
			var attributeValues = Util.getFullAttr(entity, attributeName);
			var schemaAttribute = this.getAttributeFromSchema(schema, attributeName);
			
			// if the schema does not allow for undefined attributes and one is present, fail
			if(!schemaAttribute && !schema.allowsUndefinedAttributes) {
				
				for(var idx in attributeValues) {
					
					var attributeValue = attributeValues[idx];
					validationResult = this.addValidationError(validationResult, attributeName, "Attribute not defined.");
				}
			}


			// if we have many values for a schema where hasMany = false
			if(schemaAttribute && !schemaAttribute.hasMany &&
					(attributeValues && attributeValues.length > 1)) {
				
				for(var idx in attributeValues) {
					
					var attributeValue = attributeValues[idx];
					validationResult = this.addValidationError(validationResult, attributeName, "Singleton violation");
				}
			}
			
			// if the schema has a regex, make sure all values pass
			if(schemaAttribute && schemaAttribute.validationRegex) {
				
				for(var idx in attributeValues) {
					
					var re = new RegExp(schemaAttribute.validationRegex);
					
					if(!attributeValues[idx].value.match(re)) {
						
						validationResult = this.addValidationError(validationResult, attributeName, "Failed regex")
					}
				}
			}			
		}
		
		for(var relationshipName in relationships) {
			
			var relationshipValues = Util.getFullRel(entity, relationshipName);
			var schemaRelationship = this.getRelationshipFromSchema(schema, relationshipName);
			
			// if the schema is not allowing undefined relationships & one is present, fail
			if(!schemaRelationship && !schema.allowsUndefinedRelationships && 
					(relationshipValues && relationshipValues.length > 0)) {
				
				for(var idx in relationshipValues) {
					
					validationResult = this.addValidationError(validationResult, relationshipName, "Relationship not defined")
				}
			}			

			// if we have many values for a schema where hasMany = false
			if(schemaRelationship && !schemaRelationship.hasMany &&
					(relationshipValues && relationshipValues.length > 1)) {
				
				for(var idx in relationshipValues) {
					
					validationResult = this.addValidationError(validationResult, relationshipName, "Singleton violation")
				}
			}

			// if one of the relationship values is not of the correct type
			if(schemaRelationship && schemaRelationship.entityTypeConstraint) {
				
				for(var idx in relationshipValues) {
					
					var value = relationshipValues[idx];
					
					if(!value.type.equals(schemaRelationship.entityTypeConstraint)) {
						
					    validationResult = this.addValidationError(validationResult, relationshipName, "Invalid type")
					}
				}
			}

			// check to make sure any required attributes in schema are satisfied
			if(schema.attributes) {
	
				for(var idx in schema.attributes) {
					
					var attribute = schema.attributes[idx];
					
					if(entity.attributes[attribute.name] &&
							attribute.required && !attribute.defaultValue) {
						
					    validationResult = this.addValidationError(validationResult, relationshipName, "Missing required")
					}
				}
			}

			// check to make sure any required relationships in schema are satisfied
			if(schema.relationships) {
				
				for(var idx in schema.relationships) {
					
					var relationship = schema.relationships[idx];
					
					if(entity.relationships[relationship.name] &&
							relationship.required && !relationship.defaultValue) {
						
    					validationResult = this.addValidationError(validationResult, relationshipName, "Missing required")
					}
				}
			}
		}

		return validationResult;
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
	
	getRequiredRelationships: function(schemaType) {
		
		if(typeof schemaType === 'string') {
			
			// get all required relationships here
		}
		
		else {
			
			throw new Error("Invalid argument");
		}
	},

    getRelationshipFromSchema: function(schema, relationshipName) {
        
        var relationships = schema.relationships;
        
        if(relationships) {
            for(var idx in relationships) {
            
                if(relationships[idx].name === relationshipName) {
                    return relationships[idx];
                }
            }
        }
        
        return null;
    },
    
    getAttributeFromSchema: function(schema, attributeName) {
        
        var attributes = schema.attributes;
        
        if(attributes) {
            for(var idx in attributes) {
            
                if(attributes[idx].name === attributeName) {
                
                    return attributes[idx];
                }
            }
        }

        return null;
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
	},
	
	addValidationError: function(validationMap, fieldName, errorVal) {
	    
	    if(!validationMap) {
	        validationMap = {};
	    }
	    
	    var validationFieldInfo = validationMap[fieldName];
	    
	    if(!validationFieldInfo) {
	        validationFieldInfo = [errorVal];
	    }
	    
	    validationMap[fieldName] = validationFieldInfo;
	    
	    return validationMap;
	}
}

