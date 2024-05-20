import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        while (true) {
            client.startConnection("192.168.2.162", 777);
            Scanner scanner = new Scanner(System.in);
            System.out.println("0: hello server, 1: stop");
            String greeting = scanner.nextLine();
            String response = "";
            response = client.sendMessage(greeting);
//            int choice = scanner.nextInt();
//            System.out.println(choice);
//            String response = "";
//            switch (choice) {
//                case 0:
//                    response = client.sendMessage("hello");
//                    break;
//                case 1:
//                    response = client.sendMessage("stop");
//                    break;
//            }
//            System.out.println(response);
        }
    }
}
