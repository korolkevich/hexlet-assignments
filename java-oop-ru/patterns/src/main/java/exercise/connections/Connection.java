package exercise;

public interface Connection {
    // BEGIN
    public String getCurrentState();
    public Connected connect();
    public Disconnected disconnect();
    public void write(String dataToWrite);
    // END
}
