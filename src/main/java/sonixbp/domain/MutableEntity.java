package sonixbp.domain;

import java.util.Set;

import sonixbp.memento.Caretaker;
import sonixbp.service.EntityService;

@Deprecated
public class MutableEntity implements BasicEntity, BatchedMutable {

	EntityService persistentService;
	BasicEntity entity;
	Caretaker caretaker;

	public MutableEntity(BasicEntity entity, EntityService persistentService) {

		this.entity = entity;
		this.persistentService = persistentService;
        this.caretaker = new Caretaker(entity);
	}
	
	public boolean apply() {
		
		if(entity instanceof Validateable) {
			
			if(!((Validateable) entity).validate()) {
				return false;
			}
		}
		
		try {
			
			// dump the memento to the persistent service here
			return true;
		}
		
		catch(Exception e) {
			return false;
		}
	}

	public String getType() {

		return entity.getType();
	}

	public Set<EValue> getFullAttribute(String attribute) {
		
		return entity.getFullAttribute(attribute);
	}

	public EValue getSingleAttribute(String attribute) {
		return entity.getSingleAttribute(attribute);
	}

	public Set<EValue> getFullRelationship(String relationship) {
		return entity.getFullRelationship(relationship);
	}

	public EValue getSingleRelationship(String relationship) {
		return entity.getSingleRelationship(relationship);
	}

	public void addAttribute(EValue attribute) {
		
		try {
			
			entity.addAttribute(attribute);
			
			// add to our memento here
		}
		
		catch(Exception e) {
			
			throw new RuntimeException(e);
		}
	}

	public void addRelationship(EValue relationship) {
		
		try {
			
			entity.addRelationship(relationship);
			
			// add to our memento here
			
		}
		
		catch(Exception e) {

			throw new RuntimeException(e);
		}
	}

	public Set<String> getAttributeKeySet() {

		return entity.getAttributeKeySet();
	}

	public Set<String> getRelationshipKeySet() {

		return entity.getRelationshipKeySet();
	}

	public void updateAttribute(EValue attribute) {
		
		try {
			
			entity.updateAttribute(attribute);
			
			// add to our memento here
		}
		
		catch(RuntimeException e) {
			
			throw new RuntimeException(e);
		}
	}

	public void updateRelationship(EValue relationship) {
		
		try {
			
			entity.updateRelationship(relationship);
			
			// add to our memento here
		}
		
		catch(RuntimeException e) {
			
			throw new RuntimeException(e);
		}
	}

	public void deleteAttribute(EValue attribute) {
		
		try {
			
			entity.deleteAttribute(attribute);
			
			// add to our memento here
		}
		
		catch(RuntimeException e) {
			
			throw new RuntimeException(e);
		}
		
	}

	public void deleteRelationship(EValue relationship) {
		
		try {
			entity.deleteRelationship(relationship);
			
			// add to our memento here
			
		}
		
		catch(RuntimeException e) {
			
			throw new RuntimeException(e);
		}

	}
}
