package farkaskid.client;

import farkaskid.message.Message;

import java.net.InetAddress;
import java.util.logging.Logger;

public class Client {
    private static final Logger LOGGER = Logger.getLogger(Client.class.getName());
    
    String name;
    InetAddress ip;

    public Client(String name, InetAddress ipAddress) {
        this.name = name;
        this.ip = ipAddress;
    }
    
    public void push(Message message) {
        LOGGER.info("Pushed to client");
    }

    public String getName() {
        return this.name;
    }

    public String getIP() {
        return this.ip.getCanonicalHostName();
    }
}