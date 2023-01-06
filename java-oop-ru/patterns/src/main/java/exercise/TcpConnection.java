package exercise;
import java.util.List;
import java.util.ArrayList;


// BEGIN
class TcpConnection {
    private Connection currentState;
    private String host;
    private int port;

    TcpConnection(String host, int port) {
        this.host = host;
        this.port = port;
        this.currentState = new Disconnected();
    }

    public void connect() {
        this.currentState = this.currentState.connect();
    }

    public void disconnect() {
        this.currentState = this.currentState.disconnect();
    }

    public String getCurrentState() {
        return this.currentState.getCurrentState();
    }

    public void write(String dataToWrite) {
        this.currentState.write(dataToWrite);
    }

}
// END
