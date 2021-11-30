import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Client {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private BigInteger privateKey;
    private BigInteger publicKey;
    private final BigInteger BASE = new BigInteger("3");
    private final BigInteger MOD = new BigInteger("17");

    public Client() {
        try {
            this.startConnection("127.0.0.1", 6666);
            Random r = new Random(System.currentTimeMillis());
            this.privateKey = new BigInteger(Integer.toString(r.nextInt(this.MOD.intValue())));
            this.out.println(this.BASE.modPow(this.privateKey, this.MOD));
            BigInteger otherMod = new BigInteger(this.in.readLine());
            this.publicKey = otherMod.modPow(this.privateKey, this.MOD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startConnection(String address, int port) {
        try {
            this.clientSocket = new Socket(address, port);
            this.out = new PrintWriter(this.clientSocket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        this.out.println(this.xor(message.getBytes(), Integer.toBinaryString(this.publicKey.intValue()).getBytes()));
    }

    public String getResponse() {
        try {
            return this.xor(this.in.readLine().getBytes(), Integer.toBinaryString(this.publicKey.intValue()).getBytes());
        } catch (Exception e) {
            return null;
        }
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

    private String xor(byte[] message, byte[] key) {
        byte[] xor = new byte[message.length];
        for (int i = 0; i < message.length; i++) {
            xor[i] = (byte) (message[i] ^ key[i % key.length]);
        }
        return new String(xor);
    }

    public static void main(String[] args) {
        Client client = new Client();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a filename");
        String filename = scanner.nextLine();
        String hash = MD5.getMD5(filename);
        client.sendMessage(hash);
        String response = client.getResponse();
        while (response == null) {
            response = client.getResponse();
        }
        client.sendMessage("exit");
        client.stopConnection();
        if (hash.equals(response)) {
            System.out.println("The files are equal");
        } else {
            System.out.println("The files are not equal");
        }
    }
}
