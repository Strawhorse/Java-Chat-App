package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {

//    main functions of the chat client are to (1) connect to server, (2) send messages, (3) receive messages
//establish connection to server using a Socket
//    listen to messages from server continuously

    private Socket socket = null;

//   BufferedReader to accept messages written to the console to send to server
    private BufferedReader inputConsole = null;

//    Writer to send messages to server
    private PrintWriter out = null;

//    BufferedReader to accept messages coming from the server for broadcast
    private BufferedReader in = null;


//    establish a connection to server at address and port
    public ChatClient(String address,int port) {
        try{
            socket = new Socket(address,port);
            System.out.println("Connected to the chat server at port " + Integer.toString(port));

            inputConsole = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

//            looping through input text from client
            String line = "";
            while(!line.equals("exit")) {
                line = inputConsole.readLine();
                out.println(line);
                System.out.println(in.readLine());
            }

            socket.close();
            inputConsole.close();
            out.close();

        } catch (UnknownHostException e) {
            System.out.println("Unknown host connection: " + e.getMessage());;
        } catch (IOException e) {
            System.out.println("Unexpected exception" + e);;
        }
    }


//    main method to run client
public static void main(String[] args) {
    ChatClient client = new ChatClient("127.0.0.1", 5000);
}

}
