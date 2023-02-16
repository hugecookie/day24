package day24;

import java.io.BufferedReader;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class day24_challenge_client {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("클라이언트 > 날짜 서버의 IP 주소는 ? \t");
        String address = in.nextLine();
        try (Socket client = new Socket(address, 9000);
             BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));) {
            String answer = input.readLine();
            System.out.println(answer);
        } catch (Exception e) {
            System.out.println("잘못된 접근입니다.");
        }
    }
}
