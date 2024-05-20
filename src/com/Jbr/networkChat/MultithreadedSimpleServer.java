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
            Socket socket = serverSocket.accept();

            System.out.println("Server now accepting client connections...");


            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);


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
