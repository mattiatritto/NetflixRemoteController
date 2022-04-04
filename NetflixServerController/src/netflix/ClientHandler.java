package netflix;

import com.sun.glass.events.KeyEvent;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.*;
import java.net.*;



/**
 * Classe che implementa il multithreading del server TCP.
 *
 * @author Mattia Tritto
 */

public class ClientHandler extends Thread {

    /**
     * Attributi.
     */
    
    private Socket client;

    private DataOutputStream outputToClient;
    private BufferedReader inputFromClient;

    private String clientAddress;
    private int clientPort;

    
    
    /**
     * Costruttore con parametri che inizializza il socket con il socket del client.
     *
     * @param clientSock
     */
    
    public ClientHandler(Socket clientSock) {
        this.client = clientSock;

        clientAddress = client.getInetAddress().getHostAddress();
        clientPort = client.getPort();
    }

    
    
    /**
     * Metodo eseguito quando viene invocato il metodo start.
     */
    
    public void run() {
        try {
            System.out.println("[CLIENT HANDLER]: A client is connected at " + clientAddress + ":" + clientPort + ".");
            inputFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outputToClient = new DataOutputStream(client.getOutputStream());
            startComunication();
        } catch (Exception e) {
            System.err.println("[ERROR]: An error has occured.");
        }
    }

    /**
     * Metodo che comunica con il client.
     */
    
    public void startComunication() {
        try {
            while (true) {
                String rcv = inputFromClient.readLine();
                
                System.out.println("[" + clientAddress + ":" + clientPort + "] says: " + rcv + ".");

                switch (rcv) {
                    case "play":
                        keyPress(KeyEvent.VK_SPACE);
                        break;
                    case "pause":
                        keyPress(KeyEvent.VK_SPACE);
                        break;
                    case "back":
                        keyPress(KeyEvent.VK_LEFT);
                        break;
                    case "forward":
                        keyPress(KeyEvent.VK_RIGHT);
                        break;
                    case "audio_on":
                        keyPress(KeyEvent.VK_M);
                        break;
                    case "mute":
                        keyPress(KeyEvent.VK_M);  
                        break;
                    case "close":
                        client.close();
                        break;
                    case "fullscreen":
                        keyPress(KeyEvent.VK_F);
                        break;
                    case "no_fullscreen":
                        keyPress(KeyEvent.VK_ESCAPE);
                        break;
                }
            }
        } catch (Exception e) {}
    }

    
    
    /**
     * Metodo che dato il carattere, simula la pressione del tasto.
     * 
     * @param key 
     */
    
    private void keyPress(int key) {
        Robot r = null;

        try {
            r = new Robot();
        } catch (AWTException ex) {
            System.err.println("[ERROR]: An error has occured.");
        }

        r.keyPress(key);
        r.keyRelease(key);
    }
}