package sonixbp.domain;

import java.util.Date;
import java.util.UUID;

public class Tuple {

    String id;
	String key;
	Object value;
	String classification;
	Date timestamp;

    public Tuple() {
        id = UUID.randomUUID().toString();
    }

    public Tuple(String key, Object value) {
        this();
        this.key = key;
        this.value = value;
    }

    public Tuple(Tuple eValue){
        this.key = eValue.getKey();
        this.value = eValue.getValue();
        this.id = eValue.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
