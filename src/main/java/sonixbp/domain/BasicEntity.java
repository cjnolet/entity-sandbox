package sonixbp.domain;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: cnolet
 * Date: Feb 7, 2012
 * Time: 8:40:56 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BasicEntity {

    String getType();
    
    Set<EValue> getFullAttribute(String attribute);
    EValue getSingleAttribute(String attribute);
    
    Set<EValue> getFullRelationship(String relationship);
    EValue getSingleRelationship(String relationship);
    
    void addAttribute(EValue attribute);
    void addRelationship(EValue relationship);
    
    void updateAttribute(EValue attribute);
    void updateRelationship(EValue relationship);
    
    void deleteAttribute(EValue attribute);
    void deleteRelationship(EValue relationship);
    
    Set<String> getAttributeKeySet();
    Set<String> getRelationshipKeySet();
}
