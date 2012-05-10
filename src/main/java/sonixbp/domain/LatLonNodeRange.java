package sonixbp.domain;

public class LatLonNodeRange implements NodeRange{

    double lat;
    double lon;

    public void setRange(Attribute attr) {


    }

    public boolean intersects(Attribute attr) {
        return false;
    }
}
