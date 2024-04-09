package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.function.Consumer;

public class ChatClient {

//    main functions of the chat client are to (1) connect to server, (2) send messages, (3) receive messages
//establish connection to server using a Socket
//    listen to messages from server continuously

    private Socket socket;

//     This consumer will be called with incoming messages from the server
    private Consumer<String> onMessageReceived;

//    Writer to send messages to server
    private PrintWriter out;

//    BufferedReader to accept messages coming from the server for broadcast
    private BufferedReader in;


//    establish a connection to server at address and port
    public ChatClient(String serverAddress,int serverPort, Consumer<String> onMessageReceived) throws IOException {
        this.socket = new Socket(serverAddress,serverPort);
        System.out.println("Connected to the chat server at port " + Integer.toString(serverPort));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }



        public void sendMessage(String msgText) {
        out.println(msgText);
    }


    public void startChatClient() {
        new Thread(() -> {
            try{
                String line;
                while((line = in.readLine()) != null) {
                    onMessageReceived.accept(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


}
