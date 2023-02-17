package day25;


import java.nio.channels.ServerSocketChannel;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.io.IOException;

public class ChatServer {
    public ChatServer() { System.out.println("채팅 서버를 시작합니다.");
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(5000));

            boolean running = true;
            while (running) {
                System.out.println("연결을 기다리는 중...");
                SocketChannel socketChannel = serverSocketChannel.accept();

                System.out.println("연결 되었습니다! 메시지를 입력해주세요.");
                String message;
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.print("> ");
                    message = scanner.nextLine();
                    if (message.equalsIgnoreCase("quit")) {
                        HelperMethods.sendFixedLengthMessage(socketChannel, "서버를 종료합니다.");
                                running = false;
                        break;
                    } else {
                        HelperMethods.sendFixedLengthMessage(socketChannel, message);
                        System.out.println("메시지 입력을 기다리고 있습니다....");
                        System.out.println("전달 받은 내용: " + HelperMethods.receiveFixedLengthMessage(socketChannel));
                    }
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatServer();
    }
}
