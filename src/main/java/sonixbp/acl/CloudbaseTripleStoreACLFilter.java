package sonixbp.acl;

import cloudbase.core.data.Key;
import cloudbase.core.data.Value;
import cloudbase.core.iterators.filter.Filter;
import com.google.gson.*;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;


public class CloudbaseTripleStoreACLFilter implements Filter {

    private String mode = ACLConstants.FIELD_READONLY;
    List<String> credentials = new LinkedList<String>();

    Gson gson;

    @Override
    public void init(Map<String, String> stringStringMap) {

        try {

            gson = new GsonBuilder().create();
            if(stringStringMap != null) {

                String strMode = stringStringMap.get(ACLConstants.FIELD_MODE);

                // first set the mode
                if(strMode != null && strMode.equals(ACLConstants.FIELD_READONLY) ||
                        strMode.equals(ACLConstants.FIELD_READWRITE) ||
                        strMode.equals(ACLConstants.FIELD_READWRITEDELETE)) {
                    mode = strMode;
                }

                String user = stringStringMap.get(ACLConstants.ACL_FILTER_FIELD_USER);
                if(user != null) {

                    credentials.add(user);
                }

                String groups = stringStringMap.get(ACLConstants.ACL_FILTER_FIELD_GROUPS);
                if(groups != null) {

                    credentials.addAll(Arrays.asList(groups.split(",")));
                }
            }
        }

        catch(Exception e) { }
    }

    @Override
    public boolean accept(Key key, Value value) {

        try {

            List<String> required = new LinkedList<String>();
            String rawMeta = key.getColumnQualifier().toString();

            JsonObject decoded = gson.fromJson(rawMeta, JsonElement.class).getAsJsonObject();

            if(decoded != null) {

                JsonElement element = decoded.get(mode);

                if(element != null) {

                    JsonArray requiredCreds = element.getAsJsonArray();
                    Iterator<JsonElement> itr = requiredCreds.iterator();

                    while(itr.hasNext()) {

                        required.add(itr.next().getAsString());
                    }
                }
            }

            if(required.size() > 0) {

                Collection<String> intersect = CollectionUtils.intersection(required, credentials);
                return intersect.size() > 0;
            }

            return true;
        }

        catch(Exception e) {
            return true;
        }
    }

    public static void buildACLFilterConfigMap() {

    }
}
