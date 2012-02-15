package sonixbp.listener;

import sonixbp.domain.BasicEntity;
import sonixbp.domain.EValue;

public interface AttributeListener {
	
	public void attributeUpdated(BasicEntity entity, EValue self, EValue newValue);
	public void attributeAdded(BasicEntity entity, EValue self, EValue newValue);
	public void attributeDeleted(BasicEntity entity, EValue self, EValue newValue);
	
	public void selfUpdated(BasicEntity entity, EValue self);
	public void selfAdded(BasicEntity entity, EValue self);
}
