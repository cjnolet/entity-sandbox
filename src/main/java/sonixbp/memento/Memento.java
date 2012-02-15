package sonixbp.memento;

import sonixbp.domain.EValue;

public class Memento {

    private EValue value;

    public Memento(EValue value) {
        this.value = new EValue(value);
    }

    public EValue getValue() {
        return value;
    }
}
