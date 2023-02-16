package day22;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;

public class Day22 {
    public static void main(String args[]) {
        System.out.println("멀티 캐스트 타임 클라이언트");
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(20000));
            while (true) {
                System.out.println("입력해주세요...");
                SocketChannel SC = ssc.accept();
                if (SC != null) {
                    String dattimemes = "날짜: " + new Date(System.currentTimeMillis());
                    ByteBuffer buf = ByteBuffer.allocate(64);
                    buf.put(dattimemes.getBytes());
                    buf.flip();
                    while (buf.hasRemaining()) { SC.write(buf); }
                    System.out.println("전송됨: " + dattimemes);
                }
            }
        }catch (IOException ex) {
            System.out.println("입출력예외");
    }

}
}
