package sonixbp.datatype.mapping;

import sonixbp.datatype.resolver.GemTypeResolver;

import java.lang.reflect.Method;
import java.util.List;

public class GemTypeMapping {

    /**
     * Alias should just be strings representing the mappings to (and from) the specified GemType
     */
    private List<String> aliases;

    /**
     * The GemType for which the aliases map to/from
     */
    private Class typeClass;

    /**
     * Resolver should be a string representing a class on the classpath with type: Class<? extends GemTypeResolver>
     */
    private Class<? extends GemTypeResolver> resolverClass;

    public GemTypeMapping(List<String> aliases, Class<? extends GemTypeResolver> resolverClass) {
        this.aliases = aliases;
        this.resolverClass = resolverClass;
        this.typeClass = getParameterizedTypeFromResolver();

        System.out.println(toString());
    }

    public List<String> getAliases() {
        return aliases;
    }

    public Class getTypeClass() {
        return typeClass;
    }

    public Class<? extends GemTypeResolver> getResolverClass() {

        return resolverClass;
    }

    public Class getParameterizedTypeFromResolver() {

        try {

            /**
             * The method that returns the deserialized version of the class should be the type we use to bind it
             */
           Method method = getResolverClass().getMethod("deserializeType", String.class);
           return method.getReturnType();

        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;
    }

    public String toString() {
        return new StringBuffer("gemTypeMapping: [aliases=").append(aliases).append(", typeClass=").append(typeClass.getName())
                .append(",  resolverClass=").append(resolverClass.getName()).append("]").toString();
    }
}
