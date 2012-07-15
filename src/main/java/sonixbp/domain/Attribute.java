package sonixbp.domain;

import java.util.Map;


public class Attribute<T extends Object> extends Tuple {

    private T value;

    Map<String,Object> metadata;

	public Attribute(String key, T value) {
		super(key, value);
        this.value = value;
	}

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    @Override
    public T getValue() {
        return value;
    }

    public String toString() {
        return new StringBuffer().append("attribute [key=").append(key).append(", value=").append(value).append("]").toString();
    }
}