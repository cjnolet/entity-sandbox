package sonixbp.datatype.resolver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTypeResolver implements GemTypeResolver<Date> {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'Z'");

    @Override
    public Date deserializeType(String value) {

        try {
            return sdf.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String serializeType(Date value) {
        return sdf.format(value);
    }

    @Override
    public boolean validate(Date value) {
        return value != null;
    }
}
