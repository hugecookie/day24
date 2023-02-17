package day24;

import java.net.ServerSocket;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class day24_challenge_server {
    public static void main(String[] args) {
        System.out.println("서버 시작 중...");
        try (ServerSocket socket = new ServerSocket(9000);
             Socket connetor = socket.accept();
             PrintWriter out = new PrintWriter(connetor.getOutputStream(), true);
             ) {
            out.println("서버 > " + new Date());
            } catch (Exception e) {
            System.out.println("잘못된 접근입니다.");
            }
        }

}
