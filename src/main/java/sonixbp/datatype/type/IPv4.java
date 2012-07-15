package sonixbp.datatype.type;

/**
 * GemType for representing an IPv4
 */
public class IPv4 {

    Long value;

    public IPv4(String value) {
        this.value = ipToLong(value);
    }

    public IPv4(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    private long ipToLong(String addr) {
        String[] addrArray = addr.split("\\.");

        long num = 0;
        for (int i = 0; i < addrArray.length; i++) {
            int power = 3 - i;

            num += ((Integer.parseInt(addrArray[i]) % 256 * Math.pow(256, power)));
        }

        return num;
    }

    public String toString() {

        return ((value >> 24) & 0xFF) + "." +
                ((value >> 16) & 0xFF) + "." +
                ((value >> 8) & 0xFF) + "." +
                (value & 0xFF);
    }
}
