/**
 * Created by slave00 on 14/08/16.
 */
public class Response {
    private String httpVersion;
    private String status;
    private String contentType;
    private String server;
    private String connection;
    private String contentLength;

    public Response(){
        this.setContentType("text/html");
        this.setHttpVersion("HTTP/1.1");
        this.setServer("Server: JUAO 1.0");
        this.setConnection("Connection: close");
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String type) {
        this.contentType = "Content-Type: " + type + "\n";
    }

    public String getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = "Content-Length: " + contentLength + "\n\n";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status + "\n";
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion + " ";
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server + "\n";
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection + "\n";
    }

    public void findContentType(String requestHeader){
        if(requestHeader.endsWith(".html"))
            this.setContentType("text/html");

        if(requestHeader.endsWith(".jpg"))
            this.setContentType("image/jpg");

        if(requestHeader.endsWith(".jpeg"))
            this.setContentType("image/jpeg");

        if(requestHeader.endsWith(".ico"))
            this.setContentType("image/x-icon");
    }

    @Override
    public String toString() {
        return this.getHttpVersion() + this.getStatus() + this.getContentType() + this.getServer() + this.getConnection() + this.getContentLength();
    }
}
