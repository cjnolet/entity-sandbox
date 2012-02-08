package sonixbp.domain;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: cnolet
 * Date: Feb 7, 2012
 * Time: 8:41:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class EValue {
	
	String key;
	Object value;
	String classification;
	
	Date timestamp;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
