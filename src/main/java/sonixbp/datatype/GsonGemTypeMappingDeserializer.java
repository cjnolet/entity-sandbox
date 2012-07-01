package sonixbp.datatype;

import com.google.gson.*;
import sonixbp.datatype.resolver.DatatypeResolver;
import sonixbp.datatype.type.GemType;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cnolet
 * Date: 6/30/12
 * Time: 11:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class GsonGemTypeMappingDeserializer implements JsonDeserializer<GemTypeMapping> {

  public GemTypeMapping deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {

      System.out.println("YEP!");

      JsonObject object = json.getAsJsonObject();
      JsonArray aliases = object.get("aliases").getAsJsonArray();

      System.out.println(object);
      System.out.println(aliases);

      try {

          System.out.println(object.get("typeClass"));
          Class<? extends GemType> gemType = (Class<? extends GemType>) Class.forName(object.get("typeClass").getAsString());
          Class<? extends DatatypeResolver> resolverType = (Class<? extends DatatypeResolver>)
                  Class.forName(object.get("resolverClass").getAsString());


          List<String> finalAliases = new ArrayList<String>();
          for(int i = 0; i < aliases.size(); i++) {
            finalAliases.add(aliases.get(i).getAsString());
          }

          GemTypeMapping mapping =  new GemTypeMapping(finalAliases, gemType, resolverType);

          System.out.println(mapping);

          return mapping;

      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }

      return null;

  }
}
