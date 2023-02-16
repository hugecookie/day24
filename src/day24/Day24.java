package day24;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.*;

public class Day24 {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress names[] = InetAddress.getAllByName("www.daum.net");
        for (InetAddress name : names){
            System.out.println(name);
        }
        InetAddress address = null;
        try {
            address = InetAddress.getByName("www.packtub.com");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        displayInetAddressInformation(address);

    }
    private static void
    displayInetAddressInformation(
            InetAddress address) {

        System.out.println(address);
        System.out.println("CanonicalHostName: " + address.getCanonicalHostName());
        System.out.println("HostName: " + address.getHostName());
        System.out.println("HostAddress: " + address.getHostAddress());
    }
    }

