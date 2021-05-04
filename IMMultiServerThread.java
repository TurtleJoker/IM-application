import java.net.*;
import java.io.*;

class IMMultiServerThread extends Thread {
    private Socket socket = null;

    public IMMultiServerThread(Socket socket) {
        super("IMMultiServerThread");
        this.socket = socket;
    }

    public void run() {

        try {
            PrintWriter out = 
                    new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                    
            String inputLine, outputLine, fromUser, fromServer;
            
            // Initiate conversation with client
            IMProtocol imp = new IMProtocol();
            System.out.println("Connection established!");
            outputLine = imp.processInput("Connection established!");
            out.println(outputLine);

            while ((fromUser = in.readLine()) != null) {
                if (fromUser.equals("Bye")) {
                    System.out.println("Client: Bye");
                    out.println("Bye");
                    break;
                }
                
                System.out.println("Client: " + fromUser);
                
                // Read message from keyboard.
                System.out.println("Please enter a message:");
                fromServer = stdIn.readLine();
                
                outputLine = imp.processInput(fromServer);
                out.println(outputLine);
                System.out.println("Server: " + outputLine);
                if (outputLine.equals("Bye"))
                    break;
            }
            
            System.out.println("Close connection");
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}