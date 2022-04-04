package netflix;

import java.net.*;



/**
 * Classe che realizza un server multiplo TCP.
 *
 * @author Mattia Tritto
 */

public class ServerController {

    /**
     * Attributi.
     */
    
    int serverPort;

    
    
    /**
     * Costruttore con parametri che inizializza il server
     * con la porta specificata.
     * 
     * @param serverPort
     */
    
    public ServerController(int serverPort) {
        this.serverPort = serverPort;
    }

    
    
    /**
     * Metodo che avvia il server.
     */
    
    public void start() {
        try {
            ServerSocket server = new ServerSocket(serverPort);
            while (true) {
                System.out.println("[SERVER CONTROLLER]: Netflix started on port " + serverPort + ".");
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                clientHandler.start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("[ERROR]: An error has occured.");
        }
    }
}