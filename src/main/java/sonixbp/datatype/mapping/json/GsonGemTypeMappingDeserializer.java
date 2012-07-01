package sonixbp.datatype.mapping.json;

import com.google.gson.*;
import sonixbp.datatype.mapping.GemTypeMapping;
import sonixbp.datatype.resolver.GemTypeResolver;
import sonixbp.datatype.type.GemType;

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
          Class<? extends GemType> gemType = (Class<? extends GemType>) Class.forName(object.get("typeClass").getAsString());
          Class<? extends GemTypeResolver> resolverType = (Class<? extends GemTypeResolver>)
                  Class.forName(object.get("resolverClass").getAsString());

          List<String> finalAliases = new ArrayList<String>();
          for(int i = 0; i < aliases.size(); i++) {
            finalAliases.add(aliases.get(i).getAsString());
          }

          GemTypeMapping mapping =  new GemTypeMapping(finalAliases, gemType, resolverType);

          return mapping;

      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }

      return null;

  }
}
