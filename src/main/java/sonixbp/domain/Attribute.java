package sonixbp.domain;

import java.util.Map;

public class Attribute extends Tuple {

    Map<String,Object> metadata;

	public Attribute(String key, String value) {
		super(key, value);
	}

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}
