package com.Jbr.javachatapp;

import client.ChatClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Field;

public class ChatClientInterface extends JFrame {

    private JTextArea messageArea;
    private JTextField textField;
    private ChatClient client;

    public ChatClientInterface() {
        super("Java Chat Client");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

//        set out the area for displaying messages and set it as editable
        messageArea = new JTextArea();
        messageArea.setEditable(true);
        add(new JScrollPane(messageArea), BorderLayout.CENTER);


//        set out the text area for writing and sending messages - needs a sendMessage method for the action listener to listen for
        textField = new JTextField();
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                client.sendMessage(textField.getText());
                textField.setText("");
            }
        });
        add(textField, BorderLayout.SOUTH);


//        The constructor initialises the ChatClient with the server's address and port and a method reference (this::onMessageReceived) to handle incoming messages.

        try{
            this.client = new ChatClient("127.0.0.1", 5000, this::onMessageReceived);


//          Want to be able to output the port number in the below error message so need to get all the class fields to find out which one it is
            for (Field f: getClass().getDeclaredFields()) {
                System.out.println("Field: " + f);
            }

            client.startClient();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error when connecting to the server", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }

    }


    private void onMessageReceived(String message){
        SwingUtilities.invokeLater(() -> messageArea.append(message + "\n"));
    }


//    create the instance of the interface
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {new ChatClientInterface().setVisible(true);});
    }

}
