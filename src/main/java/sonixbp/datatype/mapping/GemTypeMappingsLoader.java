package sonixbp.datatype.mapping;

import java.util.List;

/**
 * An interface to load a list of GemTypeMapping objects.
 */
public interface GemTypeMappingsLoader {

    /**
     * Loads a list of GemTypeMapping objects
     * @return
     */
    List<GemTypeMapping> loadMappings();
}
