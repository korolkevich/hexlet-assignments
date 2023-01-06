package exercise;

// BEGIN
class Connected implements Connection {
    private String buffer = "";
    private String state;

    public Connected() {
        this.state = "connected";
    }

    public String getCurrentState() {
        return this.state;
    }
    public Connected connect() {
        System.out.println("Error! Connection already connected");
        return this;
    }
    public Disconnected disconnect() {
        return new Disconnected();
    }
    public void write(String dataToWrite) {
        buffer.concat(", ");
        buffer.concat(dataToWrite);
    }
}
// END
