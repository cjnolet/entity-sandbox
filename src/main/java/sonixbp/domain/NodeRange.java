package sonixbp.domain;

public interface NodeRange {


    void setRange(Attribute attr);

    boolean intersects(Attribute attr);
}
