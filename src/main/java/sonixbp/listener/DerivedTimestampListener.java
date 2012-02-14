package sonixbp.listener;

import java.util.Date;
import java.util.Set;

import sonixbp.domain.EValue;
import sonixbp.domain.BasicEntity;

public class DerivedTimestampListener implements AttributeListener {

	public void attributeUpdated(BasicEntity entity, EValue self, EValue newValue) {

		if(newValue.getTimestamp().after(self.getTimestamp())) {
			
			self.setTimestamp(deriveFullTimestamp(entity));
		}
	}

	public void attributeAdded(BasicEntity entity, EValue self, EValue newValue) {
		
		if(newValue.getTimestamp().after(self.getTimestamp())) {
			
			self.setTimestamp(deriveFullTimestamp(entity));
		}
	}

	public void attributeDeleted(BasicEntity entity, EValue self, EValue newValue) {
		
		if(newValue.getTimestamp().equals(self.getTimestamp())) {
			
			self.setTimestamp(deriveFullTimestamp(entity));
		}
	}

	public void selfUpdated(BasicEntity entity, EValue self) {

		self.setTimestamp(deriveFullTimestamp(entity));
	}

	public void selfAdded(BasicEntity entity, EValue self) {
		
		self.setTimestamp(deriveFullTimestamp(entity));
	}
	
	private Date deriveFullTimestamp(BasicEntity entity) {
		
		Set<String> attributes = entity.getAttributeKeySet();
		Set<String> relationships = entity.getRelationshipKeySet();
	
		Date maxTimestamp = null;
		for(String attributeName : attributes) {
			
			Set<EValue> values  = entity.getFullAttribute(attributeName);
			for(EValue curValue : values) {
				
				if(maxTimestamp == null) {
					maxTimestamp = curValue.getTimestamp();
				}
				
				else {
					if(maxTimestamp.before(curValue.getTimestamp())) {
						maxTimestamp = curValue.getTimestamp();
					}
				}
			}
		}

		for(String relationshipName : relationships) {
			
			Set<EValue> values  = entity.getFullAttribute(relationshipName);
			for(EValue curValue : values) {
				
				if(maxTimestamp == null) {
					maxTimestamp = curValue.getTimestamp();
				}
				
				else {
					if(maxTimestamp.before(curValue.getTimestamp())) {
						maxTimestamp = curValue.getTimestamp();
					}
				}
			}
		}
		
		return maxTimestamp;
	}
}
