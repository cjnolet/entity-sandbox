package sonixbp.datatype.type;


public class DoubleType extends AbstractGemType<Double> {

    Double value;

    @Override
    public String getAsString() {
        return Double.toString(value);
    }

    @Override
    public Double get() {
        return value;
    }

    @Override
    public void set(Double value) {
        this.value = value;
    }
}
