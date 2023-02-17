package day24;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TimeClient {
    // NIO MultiCast Client
    public static void main(String args[]) {
        System.out.println("NIO 멀티 캐스트 타임 클라이언트");
        SocketAddress address = new InetSocketAddress("127.0.0.1", 20000);
        try (SocketChannel sc = SocketChannel.open(address)) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(64); // NON DIRECT
            int bytesRead =  sc.read(byteBuffer);
            while (bytesRead > 0) {
                byteBuffer.flip(); // limit to position, position to zero
                while (byteBuffer.hasRemaining())
                    System.out.print((char)byteBuffer.get());
                System.out.println();
                bytesRead = sc.read(byteBuffer);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}