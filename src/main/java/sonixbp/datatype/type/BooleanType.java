package sonixbp.datatype.type;


public class BooleanType extends AbstractGemType<Boolean> {

    Boolean value;

    @Override
    public String getAsString() {
        return Boolean.toString(value);
    }

    @Override
    public Boolean get() {
        return value;
    }

    @Override
    public void set(Boolean value) {
        this.value = value;
    }
}
