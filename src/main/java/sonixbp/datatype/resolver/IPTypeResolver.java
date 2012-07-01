package sonixbp.datatype.resolver;


import sonixbp.datatype.type.IPType;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPTypeResolver implements DatatypeResolver<IPType>{

    @Override
    public IPType deserializeType(String value) {

        String finalIP = "";
        for(int i = 0; i < 4; i++) {

            String theByte = value.substring(i * 8, (i * 8) + 8);
            Integer item = Integer.parseInt(theByte, 2);

            finalIP += Integer.toString(item);

            if(i < 3) {
                finalIP += ".";
            }
        }

        IPType type = new IPType();
        type.set(finalIP);
        return type;
    }

    @Override
    public String serializeType(IPType value) {

        String[] ip = value.get().split("\\.");

        String buffer = "";
        for(int i = 0; i < ip.length; i++) {

            buffer += padByte(Integer.toBinaryString(Integer.parseInt(ip[i])));
        }
        return buffer;
    }

    private String padByte(String theByte) {

        int padAmount = 8 - theByte.length();

        String padding = "";
        for(int i = 0; i < padAmount; i++) {

            padding += "0";
        }

        return padding + theByte;
    }
}
