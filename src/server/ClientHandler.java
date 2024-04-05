package server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;
import java.io.BufferedReader;
import java.io.PrintWriter;

class ClientHandler implements Runnable {

    private Socket clientSocket;
    private List<ClientHandler> clients;
    private PrintWriter out;
    private BufferedReader in;

//    create constructor for this class
    public ClientHandler(Socket clientSocket, List<ClientHandler> clients) throws IOException {
        this.clientSocket = clientSocket;
        this.clients = clients;
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    @Override
    public void run() {

//        reading messages sent by client (in) and broadcasts them to other clients
        try{
            String inputTextLine;
            while((inputTextLine = in.readLine()) !=null) {
                for(ClientHandler aClient: clients){
                    aClient.out.println(inputTextLine);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                in.close();
                out.close();
                clientSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
