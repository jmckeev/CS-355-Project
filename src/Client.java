import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public void startConnection(String address, int port) {
        try {
            this.clientSocket = new Socket(address, port);
            this.out = new PrintWriter(this.clientSocket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sendMessage(String message) {
        try {
            this.out.println(message);
            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void stopConnection() {
        try {
            this.in.close();
            this.out.close();
            this.clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.startConnection("127.0.0.1", 6666);
        new Client().startConnection("127.0.0.1", 6666);
        String line = "";
        Scanner scanner = new Scanner(System.in);
        while(!line.equals("exit")) {
            line = scanner.nextLine();
            scanner.nextLine();
            String response = client.sendMessage(line);
            System.out.println("response = " + response);
        }
        client.sendMessage("exit");
        client.stopConnection();
    }
}
