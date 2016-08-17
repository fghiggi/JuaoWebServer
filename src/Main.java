import java.io.IOException;
import java.net.Socket;

/**
 * Created by slave00 on 14/08/16.
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        WebServer ws = new WebServer();

        ws.setUp();

        while(true){
            Socket s = ws.waitForConnections();

            String request = ws.receiveRequest(s);

            ws.sendResponse(ws.processRequest(request), s);
        }
    }
}
