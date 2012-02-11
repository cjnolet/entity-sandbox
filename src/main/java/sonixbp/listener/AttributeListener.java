package sonixbp.listener;

import sonixbp.domain.EValue;
import sonixbp.domain.Entity;

public interface AttributeListener {
	
	public void attributeUpdated(Entity entity, EValue self, EValue newValue);
	public void attributeAdded(Entity entity, EValue self, EValue newValue);
	public void attributeDeleted(Entity entity, EValue self, EValue newValue);
	
	public void selfUpdated(Entity entity, EValue self);
	public void selfAdded(Entity entity, EValue self);
}
