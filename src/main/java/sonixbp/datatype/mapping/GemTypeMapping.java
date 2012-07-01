package sonixbp.datatype.mapping;

import sonixbp.datatype.resolver.GemTypeResolver;
import sonixbp.datatype.type.GemType;
import java.util.List;

public class GemTypeMapping {

    /**
     * Alias should just be strings representing the mappings to (and from) the specified GemType
     */
    private List<String> aliases;

    /**
     * The GemType for which the aliases map to/from
     */
    private Class<? extends GemType> typeClass;

    /**
     * Resolver should be a string representing a class on the classpath with type: Class<? extends GemTypeResolver>
     */
    private Class<? extends GemTypeResolver> resolverClass;

    public GemTypeMapping(List<String> aliases, Class<? extends GemType> typeClass, Class<? extends GemTypeResolver> resolverClass) {
        this.aliases = aliases;
        this.typeClass = typeClass;
        this.resolverClass = resolverClass;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public Class<? extends GemType> getTypeClass() {
        return typeClass;
    }

    public Class<? extends GemTypeResolver> getResolverClass() {

        return resolverClass;
    }

    public String toString() {
        return new StringBuffer("gemTypeMapping: [aliases=").append(aliases).append(", typeClass=").append(typeClass.getName())
                .append(",  resolverClass=").append(resolverClass.getName()).append("]").toString();
    }
}
