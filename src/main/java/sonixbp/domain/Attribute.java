package sonixbp.domain;

import sonixbp.datatype.mapping.GemDatatypeFactory;
import sonixbp.datatype.type.GemType;
import sonixbp.datatype.type.StringLiteralType;

import java.util.Map;

public class Attribute extends Tuple {

    Map<String,Object> metadata;

    Class<? extends GemType> gemType = StringLiteralType.class;

	public Attribute(String key, Object value) {
		super(key, value);
	}

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public GemType getAsGemType() {

        return GemDatatypeFactory.getInstance().buildGemTypeFromRawValue(value, gemType);
    }
}