package sonixbp.datatype.mapping.json;

import com.google.gson.*;
import sonixbp.datatype.mapping.GemTypeMapping;
import sonixbp.datatype.resolver.GemTypeResolver;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A GSON deserializer for casting a json representation of a GemTypeMapper into an instance of the object.
 * Namely, this deserializer calls Class.forName() on the strings representing the resolver/type classes.
 */
public class GsonGemTypeMappingDeserializer implements JsonDeserializer<GemTypeMapping> {

  public GemTypeMapping deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {

      JsonObject object = json.getAsJsonObject();
      JsonArray aliases = object.get("aliases").getAsJsonArray();



      try {

          /**
           * As long as Class.forName() is done once and cached, we shouldn't notice any decrease in performance after initialization
           */
//          Class gemType = Class.forName(object.get("typeClass").getAsString());
//
//          System.out.println("GEMTYPE: " + gemType);

          Class<? extends GemTypeResolver> resolverType = (Class<? extends GemTypeResolver>)
                  Class.forName(object.get("resolverClass").getAsString());

          List<String> finalAliases = new ArrayList<String>();
          for(int i = 0; i < aliases.size(); i++) {
            finalAliases.add(aliases.get(i).getAsString());
          }

          GemTypeMapping mapping =  new GemTypeMapping(finalAliases, resolverType);

          return mapping;

      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }

      return null;

  }
}
