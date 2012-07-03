package sonixbp.datatype.resolver;


import sonixbp.datatype.type.IP;

public class IPTypeResolver implements GemTypeResolver<IP> {

    @Override
    public IP deserializeType(String value) {

        String finalIP = "";
        for(int i = 0; i < 4; i++) {

            String theByte = value.substring(i * 8, (i * 8) + 8);
            Integer item = Integer.parseInt(theByte, 2);

            finalIP += Integer.toString(item);

            if(i < 3) {
                finalIP += ".";
            }
        }

        IP type = new IP(finalIP);
        return type;
    }

    @Override
    public String serializeType(IP value) {

        String[] ip = value.getValue().split("\\.");

        String buffer = "";
        for(int i = 0; i < ip.length; i++) {

            buffer += padByte(Integer.toBinaryString(Integer.parseInt(ip[i])));
        }
        return buffer;
    }

    @Override
    public boolean validate(IP value) {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
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
