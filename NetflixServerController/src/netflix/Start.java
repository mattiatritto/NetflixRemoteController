package netflix;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;



/**
 * Questa classe contiene il metodo main eseguito all'avvio dell'applicativo.
 * 
 * @author Mattia Tritto
 */

public class Start {
    
    public static void main(String[] args) throws AWTException, InterruptedException, IOException {
        Runtime.getRuntime().exec("/Users/mattiatritto/Netflix.app/Contents/MacOS/app_mode_loader", null, new File("/Users/mattiatritto/Netflix.app/Contents/MacOS"));
        
        ServerController server = new ServerController(5000);
        server.start();
    }
}