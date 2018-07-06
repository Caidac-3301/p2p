package farkaskid.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import farkaskid.client.Client;
import farkaskid.message.Message;
import farkaskid.message.MessageType;

public class Server {
    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());

    private Set<Client> clients;
    private Integer port;

    public Server() {
        this.clients = new LinkedHashSet<Client>();
    }

    public void start() {
        try (ServerSocket socket = new ServerSocket(0)){
            this.port = socket.getLocalPort();
            LOGGER.info("Server is running at " + this.port.toString());

            while (true) {
                this.handleClient(socket.accept());
            }
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
    }

    private boolean addClient(Client newClient) {
        boolean added = this.clients.add(newClient);

        if (!added) {
            return added;
        }

        return added;
    }

    private void handleClient(Socket client) throws IOException {
        InputStream input = client.getInputStream();

        this.addClient(new Client(this.extractName(input), client.getInetAddress()));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        bw.append(getClientList());
        bw.flush();
        client.close();
    }

    private String extractName(InputStream input) throws IOException {
        String name;

        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        name = reader.readLine();

        return name;
    }

    // temp method
    String getClientList() {
        StringBuilder message = new StringBuilder("Client List:\n");

        for (Client c: this.clients) {
            message.append(c.getName() + ": " + c.getIP() + "\n");
        }

        return message.toString();
    }
}
