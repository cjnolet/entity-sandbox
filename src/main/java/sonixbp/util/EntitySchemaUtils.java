package sonixbp.util;

import sonixbp.schema.EntitySchema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


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

            Map<String, EntitySchema> schema = gson.fromJson(schemaDefinition, listType);
            
            return schema.get(type); 
        }

        catch(IOException e) {

            return null;
        }
    }
}
