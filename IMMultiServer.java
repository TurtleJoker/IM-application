import java.net.*;
import java.io.*;

public class IMMultiServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        int portNumber = Integer.parseInt(args[0]);
        boolean listening = true;
        
        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("Waiting for client to connect");
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + portNumber);
            System.exit(-1);
        }
        
        while (listening) {
                new IMMultiServerThread(serverSocket.accept()).start();
        }
        
        serverSocket.close();
    }
}
