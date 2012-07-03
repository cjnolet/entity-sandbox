package sonixbp.datatype.type;


public class NonNegativeInteger {

    Integer value;

    public NonNegativeInteger(Integer input) {

        if(input < 0) {
            throw new NumberFormatException("The integer cannot be negative");
        }

        else {
            this.value = input;
        }
    }

    public Integer getValue() {
        return value;
    }

    public static NonNegativeInteger parseNonNegativeInteger(String input) {

        Integer value = Integer.parseInt(input);

        NonNegativeInteger integer;
        try {

            integer = new NonNegativeInteger(value);
            return new NonNegativeInteger(value);
        }

        catch(NumberFormatException e) {
            throw e;
        }
    }

    public static String toString(NonNegativeInteger value) {
        return Integer.toString(value.getValue());
    }
}
