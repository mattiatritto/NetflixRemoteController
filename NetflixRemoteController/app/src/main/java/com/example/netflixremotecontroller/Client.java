package com.example.netflixremotecontroller;

import android.content.Context;
import android.widget.Toast;

import java.io.*;
import java.net.*;



/**
 * Classe che realizza un client TCP in Java.
 *
 * @author Mattia Tritto
 */

public class Client {

    /**
     * Attributi
     */

    private String IPServer;
    private int portaServer;

    private Socket socket;

    private DataOutputStream outputToServer;
    private BufferedReader inputFromServer;



    /**
     * Costruttore con parametri che instaura la connessione con il server
     * specificando indirizzo IP e porta del server.
     *
     * @param IPServer
     * @param portaServer
     */

    public Client(String IPServer, int portaServer) {
        this.IPServer = IPServer;
        this.portaServer = portaServer;
        socket = connect();
    }



    /**
     * Metodo che comunica con il server.
     *
     * @param message
     */

    public void sendMessage(String message){

        try {
            System.out.println("[ANDROID]: Sending a message to the server TCP...");
            outputToServer.writeBytes(message + '\n');
        } catch (Exception e) {
            System.err.println("[ERROR]: An error has occured.");
        }
    }



    /**
     * Metodo che connette il client con il server.
     *
     * @return Socket
     */

    private Socket connect() {
        try {
            socket = new Socket(IPServer, portaServer);
            System.out.println("[ANDROID]: Connected at " + IPServer + ":" + portaServer + ".");

            outputToServer = new DataOutputStream(socket.getOutputStream());
            inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("[ERROR]: Server does not exist.");
        }  catch (Exception e) {
            System.err.println("[ERROR]: An error has occured.");
        }
        return socket;
    }
}