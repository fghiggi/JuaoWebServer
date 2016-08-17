/**
 * Created by slave00 on 15/08/16.
 */
public class Request {
    private String[] request;
    private String verb;
    private String path;

    public Request(String request){
        this.request = request.split(" ");
        this.setVerb(this.request[0]);
        this.setPath(this.request[1]);
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        if(path.equals("/"))
            path = "index.html";

        this.path = path;
    }
}
