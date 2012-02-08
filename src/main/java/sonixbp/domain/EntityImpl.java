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
public class EntityImpl implements Entity {
	
	
	private String type;
	private String id;
	
	private Map<String, Set<EValue>> attributes;
	private Map<String, Set<EValue>> relationships;
	
	public EntityImpl(String type, String id) {
		this.type = type;
		this.id = id;
		
		this.attributes = new HashMap<String,Set<EValue>>();
		this.relationships = new HashMap<String,Set<EValue>>();
	}
	
    public String getType() {
        return type;  //To change body of implemented methods use File | Settings | File Templates.
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
}
