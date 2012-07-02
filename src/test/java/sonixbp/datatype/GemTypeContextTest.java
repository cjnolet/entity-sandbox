package sonixbp.datatype;

import org.junit.Test;
import sonixbp.datatype.exception.GemTypeDeserializationFailedException;
import sonixbp.datatype.exception.GemTypeSerializationFailedException;
import sonixbp.datatype.exception.GemTypeValidationFailedException;
import sonixbp.datatype.mapping.GemTypeContext;
import sonixbp.datatype.type.*;

public class GemTypeContextTest {

    @Test
    public void testMapperFactoryInitializes() throws GemTypeValidationFailedException, GemTypeSerializationFailedException, GemTypeDeserializationFailedException {

        GemTypeContext.getInstance();

        System.out.println(GemTypeContext.getInstance().buildGemType(10L, LongType.class).getAsString());

        System.out.println(GemTypeContext.getInstance().getAliasForGemType(StringLiteralType.class));

        System.out.println(GemTypeContext.getInstance().buildGemType(10L, LongType.class));

        System.out.println(GemTypeContext.getInstance().buildGemType(true, BooleanType.class));

        System.out.println(GemTypeContext.getInstance().buildGemType(500, IntType.class));

        System.out.println(GemTypeContext.getInstance().buildGemType("TESTING!", StringLiteralType.class));

        System.out.println("172.32.90.1 = " + GemTypeContext.getInstance().serialize("172.32.90.1", IPType.class));

        System.out.println(GemTypeContext.getInstance().deserialize("10101100001000000101101000000001", IPType.class));
    }
}
