package sonixbp.domain;

import java.util.*;


public class Entity implements BasicEntity {
	
	private String type;
	private String id;
    private Date timestamp;
	
	private Map<String, Set<Attribute>> attributes;
	private Map<String, Set<Relationship>> relationships;
	
	public Entity(String type, String id) {
		this.type = type;
		this.id = id;
		
		this.attributes = new HashMap<String,Set<Attribute>>();
		this.relationships = new HashMap<String,Set<Relationship>>();
	}

    public Date getTimestamp() {
        return timestamp;
    }
	
    public String getType() {
        return type;
    }
    
    public String getId() {
    	return id;
    }

	public Set<Attribute> getFullAttribute(String attribute) {

		return attributes.get(attribute);
	}

	public Attribute getSingleAttribute(String attribute) {

		return (Attribute) attributes.get(attribute).toArray()[0];
	}

	public Set<Relationship> getFullRelationship(String relationship) {
		return relationships.get(relationship);
	}

	public Relationship getSingleRelationship(String relationship) {

		return (Relationship) relationships.get(relationship).toArray()[0];
	}

	public void addAttribute(Attribute attribute) {
		
		if(attributes.containsKey(attribute.getKey())) {
			attributes.get(attribute.getKey()).add(attribute);
		}
		
		else {
			
			Set<Attribute> attributeSet = new HashSet<Attribute>();
			attributeSet.add(attribute);
			
			attributes.put(attribute.getKey(), attributeSet);
		}
	}

	public void addRelationship(Relationship relationship) {
		
		
		if(relationships.containsKey(relationship.getKey())) {
			relationships.get(relationship.getKey()).add(relationship);
		}
		
		else {
			
			Set<Relationship> relationshipSet = new HashSet<Relationship>();
			relationshipSet.add(relationship);
			
			relationships.put(relationship.getKey(), relationshipSet);
		}
	}
	
	public Set<String> getAttributeKeySet() {
		
		return attributes.keySet();
	}
	
	public Set<String> getRelationshipKeySet() {
		
		return relationships.keySet();
	}

	public void updateAttribute(Attribute attribute) {
		
		
		
	}

	public void updateRelationship(Relationship relationship) {
		// TODO Auto-generated method stub
		
	}

	public void deleteAttribute(Attribute attribute) {
		// TODO Auto-generated method stub
		
	}

	public void deleteRelationship(Relationship relationship) {
		// TODO Auto-generated method stub
		
	}
}
