import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author slave00
 */
public class WebServer {
    private final int SERVER_PORT = 8081;
    private final String SERVER_PATH = "/home/slave00/workspace/WebServer/src/www/";

    private ServerSocket ss;
    private Request httpRequest;
    private Response httpResponse;

    public void setUp() {
        try {
            ss = new ServerSocket(SERVER_PORT);

            System.out.println("I'm running on " + SERVER_PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Socket waitForConnections() {
        try {
            return ss.accept();
        } catch (Exception e) {
            e.printStackTrace();

            throw new RuntimeException("Socket Error!");
        }
    }

    public String receiveRequest(Socket s) {
        String command = null;
        String line;

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            command = in.readLine();

            while ((line = in.readLine()).length() > 0) {
                continue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return command;
    }

    public byte[] processRequest(String request) throws IOException {
        httpRequest = new Request(request);
        httpResponse = new Response();

        System.out.println("==== REQUEST ====\n" + request);

        try{
            Path file = Paths.get(SERVER_PATH + httpRequest.getPath());

            httpResponse.setStatus("200 OK");

            return Files.readAllBytes(file);
        } catch (NoSuchFileException ex) {
            System.out.println("NOT FOUND - " + request);

            httpResponse.setStatus("404 Not Found");

            return Files.readAllBytes(Paths.get(SERVER_PATH + "404.html"));
        } catch (Exception ex){
            System.out.println("INTERNAL ERROR - " + request);

            ex.printStackTrace();

            httpResponse.setStatus("500 Internal Server Error");

            return Files.readAllBytes(Paths.get(SERVER_PATH + "500.html"));
        }
    }

    public void sendResponse(byte responseData[], Socket s) {
        try{
            OutputStream out = s.getOutputStream();

            httpResponse.findContentType(httpRequest.getPath());
            httpResponse.setContentLength(responseData.length);

            out.write(httpResponse.toString().getBytes());
            out.write(responseData);

            System.out.println("\n==== RESPONSE ====\n" + httpResponse.toString());

            out.flush();
            out.close();
            s.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}