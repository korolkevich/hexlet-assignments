package exercise;

// BEGIN
class Disconnected implements Connection {
    private String state;

    public Disconnected() {
        this.state = "disconnected";
    }

    public String getCurrentState() {
        return this.state;
    }
    public Connected connect() {
        return new Connected();
    }
    public Disconnected disconnect() {
        System.out.println("Error! Connection already disconnected");
        return this;
    }
    public void write(String dataToWrite) {
        System.out.println("Error! Connection already disconnected");
    }
}
// END
