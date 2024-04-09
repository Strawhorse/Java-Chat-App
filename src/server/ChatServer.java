package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

//    create ServerSocket to listen for incoming client connections on a port
//    when server is running, needs to continuously listen for new connections from clients and accept them
//    Usually done in a loop, new Socket object is created to handle client communication

//    Server will create a new thread for each connection -> thread will handle all communication

//    To broadcast, server receives messages from clients and then broadcasts them to other connected clients (iterating)

    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server started. Waiting for clients...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket);

//            Now create a new thread for each client connection
            ClientHandler clientThread = new ClientHandler(clientSocket, clients);
            clients.add(clientThread);

//            start a new thread
            new Thread(clientThread).start();

        }
    }
}
