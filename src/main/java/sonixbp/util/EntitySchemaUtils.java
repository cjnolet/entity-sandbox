package sonixbp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;

import sonixbp.exception.MalformedSchemaException;
import sonixbp.schema.EntitySchema;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Deprecated
public class EntitySchemaUtils {

    public static final String SCHEMA_FILENAME = "schema.json";

    public static EntitySchema parseEntitySchemaFromResource(String type) {

        Type listType = new TypeToken<Map<String, EntitySchema>>(){}.getType();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("schema.json")));

            String schemaDefinition = "";
            String buffer = reader.readLine();
            while(buffer != null) {
                schemaDefinition += buffer;
                
                buffer = reader.readLine();
            }

            System.out.println(schemaDefinition);
            Gson gson = new Gson();
            
            Map<String, EntitySchema> schema = null;
            try {
               schema = gson.fromJson(schemaDefinition, listType);
            }
            
            catch(Exception e) {
            	throw new MalformedSchemaException();
            }
            
            if(schema != null) {
            	
                return schema.get(type); 
            }
            
            else {
            	return null;
            }
        }

        catch(IOException e) {

            return null;
        }
    }
}
