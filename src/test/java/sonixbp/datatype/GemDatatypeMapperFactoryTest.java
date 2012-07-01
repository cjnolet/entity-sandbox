package sonixbp.datatype;

import org.junit.Test;
import sonixbp.datatype.mapping.GemDatatypeMapperFactory;
import sonixbp.datatype.type.BooleanType;
import sonixbp.datatype.type.IntType;
import sonixbp.datatype.type.LongType;
import sonixbp.datatype.type.StringLiteralType;

public class GemDatatypeMapperFactoryTest {

    @Test
    public void testMapperFactoryInitializes() {

        GemDatatypeMapperFactory.getInstance();

        System.out.println(GemDatatypeMapperFactory.getInstance().buildGemTypeFromRawValue(10L, LongType.class).getAsString());

        System.out.println(GemDatatypeMapperFactory.getInstance().getAliasForGemType(StringLiteralType.class));


        System.out.println(GemDatatypeMapperFactory.getInstance().buildGemTypeFromRawValue(10L, LongType.class));

        System.out.println(GemDatatypeMapperFactory.getInstance().buildGemTypeFromRawValue(true, BooleanType.class));

        System.out.println(GemDatatypeMapperFactory.getInstance().buildGemTypeFromRawValue(500, IntType.class));

        System.out.println(GemDatatypeMapperFactory.getInstance().buildGemTypeFromRawValue("TESTING!", StringLiteralType.class));



    }
}
