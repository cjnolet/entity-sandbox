package sonixbp.listener;

import sonixbp.domain.BasicEntity;
import sonixbp.domain.Tuple;

public interface AttributeListener {
	
	public void attributeUpdated(BasicEntity entity, Tuple self, Tuple newValue);
	public void attributeAdded(BasicEntity entity, Tuple self, Tuple newValue);
	public void attributeDeleted(BasicEntity entity, Tuple self, Tuple newValue);
	
	public void selfUpdated(BasicEntity entity, Tuple self);
	public void selfAdded(BasicEntity entity, Tuple self);
}
