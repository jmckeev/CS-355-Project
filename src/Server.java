import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket alice;
    private Socket bob;
    private BufferedReader aliceIn;
    private PrintWriter aliceOut;
    private BufferedReader bobIn;
    private PrintWriter bobOut;

    public void start(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            this.alice = this.serverSocket.accept();
            this.bob = this.serverSocket.accept();
            this.aliceOut = new PrintWriter(this.alice.getOutputStream(), true);
            this.aliceIn = new BufferedReader(new InputStreamReader(this.alice.getInputStream()));
            this.bobOut = new PrintWriter(this.bob.getOutputStream(), true);
            this.bobIn = new BufferedReader(new InputStreamReader(this.bob.getInputStream()));
            String line = "";
            while (!line.equals("exit")) {
                line = this.aliceIn.readLine();
                System.out.println("line = " + line);
            }
            System.out.println("Exiting");
            this.stop();
//            this.aliceOut.println("Hello Alice");
//            this.bobOut.println("Hello Bob");
//            this.aliceOut = new PrintWriter(clientSocket.getOutputStream(), true);
//            this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
//            String greeting = this.in.readLine();
//            if (greeting.equals("hello server")) {
//                this.out.println("hello client");
//            } else {
//                this.out.println("unrecognized greeting");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            this.aliceIn.close();
            this.aliceOut.close();
            this.bobIn.close();
            this.bobOut.close();
            this.alice.close();
            this.bob.close();
            this.serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start(6666);
    }
}
