package com.Jbr.networkChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 5000);

//        getting data to and from the server endpoint

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        Scanner scanner = new Scanner(System.in);

//        two variables to communicate with the server

        String requestString;
        String responseString;

        do{
            System.out.println("Enter string to be sent and echoed from server: ");
            requestString = scanner.nextLine();
            writer.println(requestString);

//            check if response came back from server
            if ((!requestString.equals("exit"))) {
                try {
                    responseString = reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(responseString);
            }

        } while (!scanner.nextLine().equals("exit"));

    }
}
