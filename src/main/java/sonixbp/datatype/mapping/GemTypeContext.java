package sonixbp.datatype.mapping;

import sonixbp.datatype.exception.GemTypeDeserializationFailedException;
import sonixbp.datatype.exception.GemTypeSerializationFailedException;
import sonixbp.datatype.exception.GemTypeValidationFailedException;
import sonixbp.datatype.mapping.impl.GemJsonTypeMappingsLoader;
import sonixbp.datatype.resolver.GemTypeResolver;
import sonixbp.datatype.type.GemType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GemTypeContext {

    /**
     * The context is lazy loaded so that a new MappingsLoader can be injected first
     */
    public static GemTypeContext context;

    /**
     * Returns the singleton instance of the DatatypeMapperFactory
     * @return
     */
    public static GemTypeContext getInstance() {

        if(context == null) {
            context = new GemTypeContext();
        }

        return context;
    }

    /**
     * Sets a new MappingsLoader
     * @param newLoader
     */
    public static void setMappingsLoader(GemTypeMappingsLoader newLoader) {
        loader = newLoader;
    }

    /**
     * Provides a default aliasMappings loader. This can be overriden by a call to "setMappingsLoader"
     * before getInstance() is called.
     */
    private static GemTypeMappingsLoader loader = new GemJsonTypeMappingsLoader("types.json");

    /**
     * Internal data structure optimized for quick lookup of alias->GemTypeMapping objects
     */
    private Map<String, GemTypeMapping> aliasMappings;

    /**
     * Internal data structure optimized for quick lookup of gemTypeClass->GemTypeMapping
     */
    private Map<Class<? extends GemType>, GemTypeMapping> typeMappings;

    /**
     * Mappings are loaded upon the singleton instance being created
     */
    public GemTypeContext() {

        aliasMappings = new HashMap<String, GemTypeMapping>();
        typeMappings = new HashMap<Class<? extends GemType>, GemTypeMapping>();

        loadMappings(loader);
    }

    /**
     * Load a set of TypeMappings using the provided MappingsLoader and normalize them into an encapsulated
     * data structure for quick lookup. Additional type mappings can be loaded by providing different loaders
     */
    public void loadMappings(GemTypeMappingsLoader loader) {

        List<GemTypeMapping> mappings = loader.loadMappings();

        /**
         * Traverse the mappings and put them in a map so that lookups can be constant time
         */
        for(GemTypeMapping typeMapping : mappings) {

            for(String type : typeMapping.getAliases()) {

                aliasMappings.put(type, typeMapping);
                typeMappings.put(typeMapping.getTypeClass(), typeMapping);
            }
        }
    }

    /**
     * Gets a GemTypeMapping given an alias
     * @param alias
     * @return
     */
    public GemTypeMapping getGemTypeMappingFromAlias(String alias) {

        return aliasMappings.get(alias);
    }

    /**
     * Gets a GemTypeMapping given a Class<? extends GemType>
     * @param gemType
     * @return
     */
    public GemTypeMapping getGemTypeMappingFromTypeClass(Class<? extends GemType> gemType) {
        return typeMappings.get(gemType);
    }

    /**
     * Gets the default alias associated with a GemType (the default alias is the first in the list of aliases
     * for a GemTypeMapper).
     * @param gemType
     * @return
     */
    public String getAliasForGemType(Class<? extends GemType> gemType) {
        return typeMappings.get(gemType).getAliases().get(0);
    }

    /**
     * Builds a GemType with the deserialized version of a [serialized] value
     * @param value
     * @param typeClazz
     * @return
     */
    public GemType deserialize(String value, Class<? extends GemType> typeClazz)
            throws GemTypeDeserializationFailedException {

        try {

            GemTypeMapping mapping = typeMappings.get(typeClazz);
            GemTypeResolver resolver = mapping.getResolverClass().newInstance();

            GemType resolvedVal = resolver.deserializeType(value);
            return resolvedVal;

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns the serialized version of a [deserialized] raw value
     * @param value
     * @param typeClazz
     * @return
     */
    public String serialize(Object value, Class<? extends GemType> typeClazz)
            throws GemTypeSerializationFailedException, GemTypeValidationFailedException {

        try {
            GemType gemType = buildGemType(value, typeClazz);

            GemTypeMapping mapping = typeMappings.get(typeClazz);
            GemTypeResolver resolver = mapping.getResolverClass().newInstance();

            if(resolver.validate(gemType)) {

                String serializedValue = resolver.serializeType(gemType);
                return serializedValue;

            }

            else {
                throw new GemTypeValidationFailedException();
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Builds a GemType instance (with the specified class type) and injects the value into it
     * @param value
     * @param typeClazz
     * @return
     */
    public GemType buildGemType(Object value, Class<? extends GemType> typeClazz) {

        try {

            GemType gemType = typeClazz.newInstance();
            gemType.set(value);

            return gemType;

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
