package sonixbp.datatype;

import org.junit.Test;
import sonixbp.datatype.exception.GemTypeDeserializationFailedException;
import sonixbp.datatype.exception.GemTypeSerializationFailedException;
import sonixbp.datatype.exception.GemTypeValidationFailedException;
import sonixbp.datatype.mapping.GemTypeContext;
import sonixbp.datatype.type.*;
import sonixbp.domain.Attribute;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

public class GemTypeContextTest {

    @Test
    public void testMapperFactoryInitializes() throws GemTypeValidationFailedException, GemTypeSerializationFailedException, GemTypeDeserializationFailedException, URISyntaxException {

        GemTypeContext.getInstance();

        /**
         * Test serialization
         */
        System.out.println("Boolean: " + GemTypeContext.getInstance().serialize(true));
        System.out.println("Long: " + GemTypeContext.getInstance().serialize(10L));
        System.out.println("Integer: " + GemTypeContext.getInstance().serialize(500000));
        System.out.println("String Literal: " + GemTypeContext.getInstance().serialize("HELLO!"));
        System.out.println("Double: " + GemTypeContext.getInstance().serialize(15.0));
        System.out.println("Byte:" + GemTypeContext.getInstance().serialize(Byte.parseByte("001")));
        System.out.println("NonNegative Integer: " + GemTypeContext.getInstance().serialize(NonNegativeInteger.parseNonNegativeInteger("5")));
        System.out.println("Date: " + GemTypeContext.getInstance().serialize(new Date()));

        /**
         * Test get Alias
         */
        System.out.println("Alias for String Literal: " + GemTypeContext.getInstance().getAliasForGemType(String.class));

//
        /**
         * Test special types
         */
        IPv4 type = new IPv4("172.32.90.1");

        System.out.println("172.32.90.1 = " + GemTypeContext.getInstance().serialize(type));
        System.out.println(GemTypeContext.getInstance().deserialize("10101100001000000101101000000001", IPv4.class));

        URI uri = new URI("hello.com");
        System.out.println(GemTypeContext.getInstance().serialize(uri));
        System.out.println(GemTypeContext.getInstance().deserialize("http://www.hello.com", URI.class));

        Attribute<Date> attr = new Attribute<Date>("key", new Date());

        System.out.println(attr);
    }
}
