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


            // get Alice mod and Bob mod and send to appropriate parties

            int aliceMod = Integer.parseInt(this.aliceIn.readLine());
            int bobMod = Integer.parseInt(this.bobIn.readLine());
            this.aliceOut.println(bobMod);
            this.bobOut.println(aliceMod);

            // send hashes to each other

            this.aliceOut.println(this.bobIn.readLine());
            this.bobOut.println(this.aliceIn.readLine());

            // wait for communication to stop

//            String aliceExit = "";
//            String bobExit = "";
//            while (!aliceExit.equals("exit") && !bobExit.equals("exit")) {
//                aliceExit = this.aliceIn.readLine();
//                if (aliceExit == null) {
//                    aliceExit = "";
//                }
//                bobExit = this.bobIn.readLine();
//                if (bobExit == null) {
//                    bobExit = "";
//                }
//            }
            System.out.println("Exiting");
            this.stop();
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
