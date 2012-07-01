package sonixbp.datatype;

import org.junit.Test;
import sonixbp.datatype.mapping.GemDatatypeFactory;
import sonixbp.datatype.type.BooleanType;
import sonixbp.datatype.type.IntType;
import sonixbp.datatype.type.LongType;
import sonixbp.datatype.type.StringLiteralType;

public class GemDatatypeMapperFactoryTest {

    @Test
    public void testMapperFactoryInitializes() {

        GemDatatypeFactory.getInstance();

        System.out.println(GemDatatypeFactory.getInstance().buildGemTypeFromRawValue(10L, LongType.class).getAsString());

        System.out.println(GemDatatypeFactory.getInstance().getAliasForGemType(StringLiteralType.class));


        System.out.println(GemDatatypeFactory.getInstance().buildGemTypeFromRawValue(10L, LongType.class));

        System.out.println(GemDatatypeFactory.getInstance().buildGemTypeFromRawValue(true, BooleanType.class));

        System.out.println(GemDatatypeFactory.getInstance().buildGemTypeFromRawValue(500, IntType.class));

        System.out.println(GemDatatypeFactory.getInstance().buildGemTypeFromRawValue("TESTING!", StringLiteralType.class));



    }
}
