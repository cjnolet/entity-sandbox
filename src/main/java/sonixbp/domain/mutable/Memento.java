package sonixbp.domain.mutable;

import sonixbp.domain.EValue;
import sonixbp.schema.AttributeType;

public class Memento {
    private EValue value;
    private AttributeType type;

    public Memento(EValue value, AttributeType type) {
        this.value = value;
        this.type = type;
    }

    public EValue getValue() {
        return value;
    }

    public AttributeType getType() {
        return type;
    }
}
