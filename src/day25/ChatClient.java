package day25;

import java.io.IOException;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class ChatClient {
    public ChatClient() {
        SocketAddress address = new InetSocketAddress("127.0.0.1", 5000);
        try (SocketChannel socketChannel = SocketChannel.open(address)) {
            System.out.println("서버와 연결 중입니다.");
                    String message;
            Scanner scanner = new
                    Scanner(System.in);
            while (true) {
                System.out.println("서버로 부터 메시지를 기다리는 중입니다. ...");
                System.out.print("> ");
                message = scanner.nextLine();
                if (message.equalsIgnoreCase("quit")) {
                    HelperMethods.sendFixedLengthMessage(socketChannel, "연결을 해지합니다.");
                    break;
                }

                HelperMethods.sendFixedLengthMessage(socketChannel, message);
                System.out.println("전달 받은 내용: " + HelperMethods.receiveFixedLengthMessage(socketChannel));


            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatClient();
    }
}
