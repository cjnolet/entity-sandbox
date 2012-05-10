package sonixbp.schema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import sonixbp.exception.MalformedSchemaException;
import sonixbp.exception.MissingSchemaException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class EntitySchema {
	
	private boolean allowsUndefinedAttributes = false;
	private boolean allowsUndefinedRelationships = false;
	private List<SchemaAttribute> attributes;
	private List<SchemaRelationship> relationships;

	public List<SchemaAttribute> getAttributes() {
		return attributes;
	}

	public List<SchemaRelationship> getRelationships() {
		return relationships;
	}

	public String toString() {
		return "attributes= [" + attributes != null ? attributes.toString() : "" + "], " + 
			   "relationships = [" + relationships != null ? relationships.toString() : "" + "]";
	}
	
	public boolean allowsUndefinedAttributes() {
		return allowsUndefinedAttributes;
	}
	
	public boolean allowsUndefinedRelationships() {
		return allowsUndefinedRelationships;
	}
	
	public String getDefaultValueForAttribute(String attribute) {
		
		if(attributes != null) {
			for(SchemaAttribute sAttr : attributes) {
				if(sAttr.getName().equals(attribute)) {
					return sAttr.getDefaultValue();
				}
			}
		}
		
		return null;
	}
	
	public SchemaAttribute getAttribute(String attribute) {

		if(attributes != null) {
			for(SchemaAttribute sAttr : attributes) {
				if(sAttr.getName().equals(attribute)) {
					return sAttr;
				}
			}
		}

		return null;
	}
	
	public SchemaRelationship getRelationship(String relationship) {

		if(relationships != null) {
		
			for(SchemaRelationship sRel : relationships) {
				if(sRel.getName().equals(relationship)) {
					return sRel;
				}
			}
		}
		
		return null;
	}
	
    public static EntitySchema parseEntitySchemaFromResource(String schemaFilename, String type) {

        Type listType = new TypeToken<Map<String, EntitySchema>>(){}.getType();

        try {
        	
        	InputStream is = Thread.currentThread()
        						.getContextClassLoader()
        						.getResourceAsStream(schemaFilename);
        	
        	if(is == null) {
        		throw new MissingSchemaException();
        	} 

        	BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String schemaDefinition = "";
            String buffer = reader.readLine();
            while(buffer != null) {
                schemaDefinition += buffer;
                
                buffer = reader.readLine();
            }
            
            Gson gson = new Gson();
            
            Map<String, EntitySchema> schema = null;
            try {
               schema = gson.fromJson(schemaDefinition, listType);
               
               System.out.println(schemaDefinition);
            }
            
            catch(Exception e) {
            	throw new MalformedSchemaException(schemaFilename);
            }
            
            if(schema != null) {
            	
            	if(schema.get(type) != null) {
                    return schema.get(type); 
            	}
            	
                else {
                	
                	throw new MissingSchemaException();
                }
            }
            
            else {
            	throw new MissingSchemaException();
            }
        }

        catch(IOException e) {

            throw new MissingSchemaException();
        }
    }
}
