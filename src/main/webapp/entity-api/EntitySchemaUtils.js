var entity = entity ? entity : {};
entity.api = entity.api ? entity.api : {};

entity.api.EntitySchemaContext = function(schemaUrl) {
	
	this.schemaUrl = schemaUrl ? schemaUrl : "entity-service/schema.json";

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
    
    return this;
}

entity.api.EntitySchemaContext.prototype = {
		
	validate: function(entity) {
		
		alert("validate called");
	},
	
	getRequiredAttributes: function(schemaType) {

		if(typeof schemaType === 'string') {
		
			// get all required attributes here
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

