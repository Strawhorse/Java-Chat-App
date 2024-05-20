package com.Jbr.networkChat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultithreadedSimpleServer {

    public static void main(String[] args) throws IOException {

        ExecutorService executorService = Executors.newCachedThreadPool();


        try (ServerSocket serverSocket = new ServerSocket(5000)) {

            while (true) {

                Socket socket = serverSocket.accept();

                socket.setSoTimeout(900_0000);

                System.out.println("Server now accepting client connections...");

                executorService.submit(() -> handleClientRequest(socket));

            }
        }
        catch (IOException e) {
            System.out.println("Error arose, error: " + e);
        }
    }

    private static void handleClientRequest(Socket socket){

        try(socket;
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        ) {
            while (true) {
                String stringToBeEchoed = reader.readLine();

                System.out.println("Server received the text: " + stringToBeEchoed);

                if (stringToBeEchoed.equals("exit")) {
                    break;
                }

                writer.println("Message back from server: " + stringToBeEchoed);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
