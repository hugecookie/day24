package day22;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
// 165.246.115.165
public class ThreadedServer implements Runnable {
    // 다중 접속 에코 서버
    private static Socket clientSocket;
    public ThreadedServer(Socket clientSocket)
    {
        this.clientSocket = clientSocket;
    }
    // 다중 접속 에코 서버
        public static void main(String[] args) {
            System.out.println("다중 접속 에코 서버");

            try (ServerSocket serverSocket = new ServerSocket(20000)) {
                while (true) {
                    System.out.println("클라이언트 대기 중.....");
                    clientSocket = serverSocket.accept();
                    ThreadedServer tes = new ThreadedServer(clientSocket);
                    new Thread(tes).start();
                }
            } catch (IOException ex) {
                // Handle exceptions
            }
            System.out.println("다중 접속 에코 서버 종료");
        }

    @Override
    public void run() {
        System.out.println("[" + Thread.currentThread() + "] 스레드 : ");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                System.out.println("[" + Thread.currentThread() + "] 클라이언트가 보낸 메세지 :" + inputLine);
                out.println(inputLine);
            }
            System.out.println("[" + Thread.currentThread() + "] 클라이언트가 종료됨.");
        } catch (IOException ex) {
            // Handle exceptions
        }
    }
}