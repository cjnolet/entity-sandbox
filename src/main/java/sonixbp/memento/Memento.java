package sonixbp.memento;

import sonixbp.domain.Tuple;

public class Memento {

    private Tuple value;

    public Memento(Tuple value) {
        this.value = new Tuple(value);
    }

    public Tuple getValue() {
        return value;
    }
}
