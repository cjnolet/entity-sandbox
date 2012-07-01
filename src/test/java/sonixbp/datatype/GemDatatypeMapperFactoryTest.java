package sonixbp.datatype;

import org.junit.Test;
import sonixbp.datatype.exception.GemTypeValidationFailedException;
import sonixbp.datatype.mapping.GemDatatypeFactory;
import sonixbp.datatype.type.*;

public class GemDatatypeMapperFactoryTest {

    @Test
    public void testMapperFactoryInitializes() throws GemTypeValidationFailedException {

        GemDatatypeFactory.getInstance();

        System.out.println(GemDatatypeFactory.getInstance().buildGemTypeFromRawValue(10L, LongType.class).getAsString());

        System.out.println(GemDatatypeFactory.getInstance().getAliasForGemType(StringLiteralType.class));


        System.out.println(GemDatatypeFactory.getInstance().buildGemTypeFromRawValue(10L, LongType.class));

        System.out.println(GemDatatypeFactory.getInstance().buildGemTypeFromRawValue(true, BooleanType.class));

        System.out.println(GemDatatypeFactory.getInstance().buildGemTypeFromRawValue(500, IntType.class));

        System.out.println(GemDatatypeFactory.getInstance().buildGemTypeFromRawValue("TESTING!", StringLiteralType.class));

        System.out.println(GemDatatypeFactory.getInstance().mapRawValueToSerializedValue("172.32.90.1", IPType.class));

        System.out.println(GemDatatypeFactory.getInstance().mapSerializedValueToGemType("10101100001000000101101000000001", IPType.class));

    }
}
