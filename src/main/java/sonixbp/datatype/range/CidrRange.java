package sonixbp.datatype.range;

import sonixbp.datatype.type.IPv4;


public class CidrRange implements ValueRange<IPv4> {

    IPv4 lowIp;
    IPv4 highIp;

    public CidrRange(IPv4 ip, int cidr) {

        SubnetUtils utils = new SubnetUtils(String.format("%s/%d", ip.toString(), cidr));

        SubnetUtils.SubnetInfo info = utils.getInfo();
        lowIp = new IPv4(info.getLowAddress());
        highIp = new IPv4(info.getHighAddress());
    }

    @Override
    public IPv4 getStart() {
        return lowIp;
    }

    @Override
    public IPv4 getStop() {
        return highIp;
    }
}
