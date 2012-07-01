package sonixbp.datatype;

import org.apache.commons.el.StringLiteral;
import org.junit.Test;
import sonixbp.datatype.type.LongType;
import sonixbp.datatype.type.StringLiteralType;

public class GemDatatypeMapperFactoryTest {

    @Test
    public void testMapperFactoryInitializes() {

        GemDatatypeMapperFactory.getInstance();

        System.out.println(GemDatatypeMapperFactory.getInstance().buildGemTypeFromRawValue(10L, LongType.class).getAsString());

        System.out.println(GemDatatypeMapperFactory.getInstance().getAliasForGemType(StringLiteralType.class));

    }
}
