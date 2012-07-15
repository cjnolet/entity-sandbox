package sonixbp.type;

import org.junit.Test;
import sonixbp.datatype.range.CidrRange;
import sonixbp.datatype.resolver.IPv4TypeResolver;
import sonixbp.datatype.type.IPv4;

public class IPv4Test {

    @Test
    public void testIPv4() {

        IPv4 ip = new IPv4("1.2.1.1");

        IPv4TypeResolver resolver = new IPv4TypeResolver();

        System.out.println(resolver.serializeType(ip));
        System.out.println(resolver.deserializeType(ip.getValue().toString()));

        CidrRange range = new CidrRange(ip, 16);

        System.out.println("START: " + resolver.serializeType(range.getStart()));
        System.out.println("START1: " + range.getStart().toString());
        System.out.println("STOP: " + resolver.serializeType(range.getStop()));
        System.out.println("STOP1: " + range.getStop().toString());
    }
}
