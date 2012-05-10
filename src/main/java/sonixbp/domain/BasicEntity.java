package sonixbp.domain;

import java.util.Set;


public interface BasicEntity {

    String getType();

     Set<Attribute> getFullAttribute(String attribute);
    Attribute getSingleAttribute(String attribute);
    
    Set<Relationship> getFullRelationship(String relationship);
    Relationship getSingleRelationship(String relationship);
    
    void addAttribute(Attribute attribute);
    void addRelationship(Relationship relationship);
    
    void updateAttribute(Attribute attribute);
    void updateRelationship(Relationship relationship);
    
    void deleteAttribute(Attribute attribute);
    void deleteRelationship(Relationship relationship);
    
    Set<String> getAttributeKeySet();
    Set<String> getRelationshipKeySet();
}