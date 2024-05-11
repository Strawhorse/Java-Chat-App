package com.Jbr.networkChat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultithreadedSimpleServer {

    public static void main(String[] args) throws IOException {

        //    just echo back to client what it sends
        ServerSocket serverSocket = new ServerSocket(5000);


        while(true) {
            //    once you have a server socket instance you can wait for the clients acceptance of the socket
            Socket socket = serverSocket.accept();

            System.out.println("Server now accepting client connections...");

            socket.setSoTimeout(20000);

//        set up a buffered reader for the input from the client
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

//      Set up output writer for the output to the client after being sent to the server; and autoflush = true
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

//        listen for input
            while (true) {
                String stringToBeEchoed = reader.readLine();

                System.out.println("Server received the text: " + stringToBeEchoed);

                if(stringToBeEchoed.equals("exit")){
                    break;
                }

                writer.println("Message back from server: " + stringToBeEchoed);
            }
        }
    }
}
