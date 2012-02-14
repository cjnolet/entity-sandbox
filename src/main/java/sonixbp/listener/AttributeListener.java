package sonixbp.listener;

import sonixbp.domain.EValue;
import sonixbp.domain.BasicEntity;

public interface AttributeListener {
	
	public void attributeUpdated(BasicEntity entity, EValue self, EValue newValue);
	public void attributeAdded(BasicEntity entity, EValue self, EValue newValue);
	public void attributeDeleted(BasicEntity entity, EValue self, EValue newValue);
	
	public void selfUpdated(BasicEntity entity, EValue self);
	public void selfAdded(BasicEntity entity, EValue self);
}
