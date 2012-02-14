package sonixbp.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: cnolet
 * Date: Feb 7, 2012
 * Time: 8:41:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class Entity implements BasicEntity {
	
	private String type;
	private String id;
	
	private Map<String, Set<EValue>> attributes;
	private Map<String, Set<EValue>> relationships;
	
	public Entity(String type, String id) {
		this.type = type;
		this.id = id;
		
		this.attributes = new HashMap<String,Set<EValue>>();
		this.relationships = new HashMap<String,Set<EValue>>();
	}
	
    public String getType() {
        return type;
    }
    
    public String getId() {
    	return id;
    }

	public Set<EValue> getFullAttribute(String attribute) {

		return attributes.get(attribute);
	}

	public EValue getSingleAttribute(String attribute) {

		return (EValue) attributes.get(attribute).toArray()[0];
	}

	public Set<EValue> getFullRelationship(String relationship) {
		return relationships.get(relationship);
	}

	public EValue getSingleRelationship(String relationship) {

		return (EValue) relationships.get(relationship).toArray()[0];
	}

	public void addAttribute(EValue attribute) {
		
		if(attributes.containsKey(attribute.getKey())) {
			attributes.get(attribute.getKey()).add(attribute);
		}
		
		else {
			
			Set<EValue> attributeSet = new HashSet<EValue>();
			attributeSet.add(attribute);
			
			attributes.put(attribute.getKey(), attributeSet);
		}
	}

	public void addRelationship(EValue relationship) {
		
		
		if(relationships.containsKey(relationship.getKey())) {
			relationships.get(relationship.getKey()).add(relationship);
		}
		
		else {
			
			Set<EValue> relationshipSet = new HashSet<EValue>();
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

	public void updateAttribute(EValue attribute) {
		
		
		
	}

	public void updateRelationship(EValue relationship) {
		// TODO Auto-generated method stub
		
	}

	public void deleteAttribute(EValue attribute) {
		// TODO Auto-generated method stub
		
	}

	public void deleteRelationship(EValue relationship) {
		// TODO Auto-generated method stub
		
	}
}
