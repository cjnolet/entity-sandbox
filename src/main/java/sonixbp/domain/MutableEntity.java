package sonixbp.domain;

import java.util.Set;

import sonixbp.memento.Caretaker;
import sonixbp.service.EntityService;

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
			
			this.persistentService.save(this.entity);

			return true;
		}
		
		catch(Exception e) {
			return false;
		}
	}

    public void rollback() {
    }

    public String getType() {

		return entity.getType();
	}

	public Set<Attribute> getFullAttribute(String attribute) {
		
		return entity.getFullAttribute(attribute);
	}

	public Attribute getSingleAttribute(String attribute) {
		return entity.getSingleAttribute(attribute);
	}

	public Set<Relationship> getFullRelationship(String relationship) {
		return entity.getFullRelationship(relationship);
	}

	public Relationship getSingleRelationship(String relationship) {
		return entity.getSingleRelationship(relationship);
	}

	public void addAttribute(Attribute attribute) {
		
		try {
			
			entity.addAttribute(attribute);
			
			// add to our memento here
		}
		
		catch(Exception e) {
			
			throw new RuntimeException(e);
		}
	}

	public void addRelationship(Relationship relationship) {
		
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

	public void updateAttribute(Attribute attribute) {
		
		try {
			
			entity.updateAttribute(attribute);
			
			// add to our memento here
		}
		
		catch(RuntimeException e) {
			
			throw new RuntimeException(e);
		}
	}

	public void updateRelationship(Relationship relationship) {
		
		try {
			
			entity.updateRelationship(relationship);
			
			// add to our memento here
		}
		
		catch(RuntimeException e) {
			
			throw new RuntimeException(e);
		}
	}

	public void deleteAttribute(Attribute attribute) {
		
		try {
			
			entity.deleteAttribute(attribute);
			
			// add to our memento here
		}
		
		catch(RuntimeException e) {
			
			throw new RuntimeException(e);
		}
		
	}

	public void deleteRelationship(Relationship relationship) {
		
		try {
			entity.deleteRelationship(relationship);
			
			// add to our memento here
			
		}
		
		catch(RuntimeException e) {
			
			throw new RuntimeException(e);
		}

	}



}
