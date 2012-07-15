package sonixbp.domain;

/**
 * Created by IntelliJ IDEA.
 * User: cnolet
 * Date: 7/12/12
 * Time: 10:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class Triple {

    String subject;
    String predicate;
    String object;

    String type;
    String visibility;

    public String getSubject() {
        return subject;
    }

    public String getPredicate() {
        return predicate;
    }

    public String getObject() {
        return object;
    }

    public String getType() {
        return type;
    }

    public String getVisibility() {
        return visibility;
    }
}
