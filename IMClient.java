import java.io.*;
import java.net.*;
 
public class IMClient {
    public static void main(String[] args) throws IOException {
        Socket imSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        
        String serverIP = "localhost";
        int portNumber = Integer.parseInt(args[1]);
        try {
            imSocket = new Socket(serverIP, portNumber);
            out = new PrintWriter(imSocket.getOutputStream(), true);
            in = new BufferedReader(
                new InputStreamReader(imSocket.getInputStream()));
            BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
            
            String fromServer,fromUser; 
            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye"))
                    break;
                 
                // Read message from keyboard.
                System.out.println("Please enter a message:");
                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + serverIP);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                serverIP);
            System.exit(1);
        }
    }
}