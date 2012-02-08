package sonixbp.util;

import sonixbp.schema.EntitySchema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.code.Gson;

/**
 * Created by IntelliJ IDEA.
 * User: cnolet
 * Date: Feb 7, 2012
 * Time: 8:42:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class EntitySchemaUtils {


    public static final String SCHEMA_FILENAME = "schema.json";

    public static EntitySchema parseEntitySchemaFromResource(String type) {

        Type listType = new TypeToken<List<EntitySchema>>(){}.getType();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("schema.json")));

            String schemaDefinition = "";
            String buffer;
            while(buffer = reader.readLine() != null) {
                schemaDefinition = buffer;
            }

            Gson gson = new Gson();

            List<EntitySchema> schema = gson.fromJson(schemaDefinition, listType);
        }

        catch(IOException e) {

            return null;
        }
    }
}
