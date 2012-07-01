package sonixbp.datatype.mapping.impl;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import sonixbp.datatype.mapping.GemTypeMapping;
import sonixbp.datatype.mapping.GemTypeMappingsLoader;
import sonixbp.datatype.mapping.json.GsonGemTypeMappingDeserializer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * A MappingsLoader that extracts the list of GemTypeMapping objects from a json file on the classpath. The json
 * file should contain a json array of objects in the following format:
 * { aliases: ["@en"], typeClass: "sonixbp.datatype.type.StringLiteralType", resolverClass: "sonixbp.datatype.resolver.StringLiteralTypeResolver" }
 */
public class GemJsonTypeMappingsLoader implements GemTypeMappingsLoader {

    private String filename;

    public GemJsonTypeMappingsLoader(String filename) {
        this.filename = filename;
    }

    /**
     * The mappings are loaded into a string and then parsed from GSON
     * @return
     */
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

            GsonBuilder gson = new GsonBuilder();
            gson.registerTypeAdapter(GemTypeMapping.class, new GsonGemTypeMappingDeserializer());

            List<GemTypeMapping> typeMappings = null;
            try {
               typeMappings = gson.create().fromJson(mappings, listType);
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
