package farkaskid.message;

public class Message {
    MessageType type;
    String message;

    public Message(MessageType type, String message) {
        this.type = type;
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
