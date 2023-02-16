package day23;

import java.net.NetworkInterface;
import java.net.SocketException;

public class Test {
    public static void main(String[] args) {
//        getMACIdentifier();



    }

    private static String getMACIdentifier(NetworkInterface network) {
        StringBuilder identifier = new StringBuilder();
        try {
            byte[] macBuffer = network.getHardwareAddress();
            if (macBuffer != null) {
                for (int i = 0; i < macBuffer.length; i++) {
                    identifier.append(String.format("%02X%s",
                            macBuffer[i], (i < macBuffer.length - 1) ? "-" : ""));
                }
            } else {
                return "---";
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return identifier.toString();
    }
}

