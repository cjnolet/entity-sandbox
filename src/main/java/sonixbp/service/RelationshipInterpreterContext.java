package sonixbp.service;


import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class RelationshipInterpreterContext {

    private Map<String, RelationshipInterpreter> interpreters;

    public RelationshipInterpreterContext() {
        interpreters = new HashMap<String, RelationshipInterpreter>();
    }

    public void addNamespace(String namespace, RelationshipInterpreter interpreter) {

        interpreters.put(namespace, interpreter);
    }

    public RelationshipInterpreter getNamespaceInterpreter(URI link) {

        return interpreters.get(link.getScheme().toLowerCase());
    }
}
