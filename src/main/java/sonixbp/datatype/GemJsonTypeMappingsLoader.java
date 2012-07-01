package sonixbp.datatype;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * A MappingsLoader that extracts the list of GemTypeMapping objects from a json file on the classpath
 */
public class GemJsonTypeMappingsLoader implements GemTypeMappingsLoader {

    private String filename;

    public GemJsonTypeMappingsLoader(String filename) {
        this.filename = filename;
    }

    @Override
    public List<GemTypeMapping> loadMappings() {

        Type listType = new TypeToken<List<GemTypeMapping>>(){}.getType();

        try {

            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String mappings = "";
            String buffer = reader.readLine();
            while(buffer != null) {
                mappings += buffer;

                buffer = reader.readLine();
            }

            System.out.println(mappings);

            GsonBuilder gson = new GsonBuilder();
            gson.registerTypeAdapter(GemTypeMapping.class, new GsonGemTypeMappingDeserializer());

            List<GemTypeMapping> typeMappings = null;
            try {
               typeMappings = gson.create().fromJson(mappings, listType);

               System.out.println("TYPE MAPPINGS: " + typeMappings);

            }

            catch(Exception e) {
                e.printStackTrace();
            }

            return typeMappings;
        }

        catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
