package day22;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 165.246.115.165
public class ThreadedServer implements Runnable {
    // 다중 접속 에코 서버
    private static Socket clientSocket;

    public ThreadedServer(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    // 다중 접속 에코 서버
    public static void main(String[] args) {
        ExecutorService eService = Executors.newFixedThreadPool(2);  // 2 thread
        System.out.println("다중 접속 에코 서버");

        try (ServerSocket serverSocket = new ServerSocket(20000)) {
            while (true) {
                System.out.println("클라이언트 대기 중.....");
                clientSocket = serverSocket.accept();
                ThreadedServer tes = new ThreadedServer(clientSocket);
                //  new Thread(tes).start();  // infinite thread
                eService.submit(tes);
            }
        } catch (IOException ex) {
            System.out.println("입출력 예외 발생");
        }
        System.out.println("다중 접속 에코 서버 종료");
        eService.shutdown();
    }

    @Override
    public void run() {
        System.out.println("[" + Thread.currentThread() + "] 스레드 : ");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(clientSocket.getRemoteSocketAddress().toString() + " " + Thread.currentThread() +" 클라이언트가 보낸 메세지 : " + line);
                try{
                    StringTokenizer st = new StringTokenizer(line,"+-*/", true);
                    int result = 0, operand = 0;
                    char operator = '+';
                    while(st.hasMoreTokens()){
                        String token = st.nextToken().trim();
                        if("+-*/".contains(token)){
                            operator = token.charAt(0);
                        }else{
                            operand = Integer.parseInt(token);
                            switch (operator) {
                                case '+' -> result = result + operand;
                                case '-' -> result = result - operand;
                                case '*' -> result = result * operand;
                                case '/' -> result = result / operand;
                            }
                                }
                            }
                            out.println(line+"="+result);
                        }catch (NumberFormatException err){
                            out.println("유효하지 않은 입력 값 입니다. 숫자를 입력해주세요.");
                        }
                    }
                    System.out.println(Thread.currentThread() +" 클라이언트가 종료됨"); }
                catch (IOException ex)
                {
                    System.out.println("입출력 예외 발생!");
                }
    }
}
