package p2p.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import p2p.message.MessageListener;

public class ClientImpl implements Client {

    private int port;

    private ServerSocket serverSocket;

    private ServerSocket peerDiscoverySocket;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private List<Socket> receivingList = new ArrayList<>();

    private List<Socket> sendingList = new ArrayList<>();

    public ClientImpl(int port, int peerDiscoveryPort) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(this.port);

        this.peerDiscoverySocket = new ServerSocket(peerDiscoveryPort);

        listenIncomingConnections();
        sendMessage();
        listenForNewpeers();
    }

    public void listenForNewpeers() throws IOException {
        Socket incomingPeer = this.peerDiscoverySocket.accept();

        new Thread(new PeerListener(incomingPeer, sendingList)).start();
    }

    public void listenIncomingConnections() {
        new Thread(() -> {
            while (true) {
                try {
                    Socket incomingConnection = this.serverSocket.accept();
                    this.receivingList.add(incomingConnection);
                    new Thread(new MessageListener(incomingConnection.getInputStream())).start();
				} catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Can not accept incoming connection.");
				}
            }
        }).start();
    }

    public void sendMessage() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        new Thread(() -> {
            while (true) {
                try {
                    String message = bufferedReader.readLine() + "\n";
                    for (Socket peer: this.sendingList) {
                        new DataOutputStream(peer.getOutputStream()).write(message.getBytes());
                    }

				} catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Can not read from console.");
				}
            }
        }).start();
    }

    public void listenIncomingMessages() {
        for (Socket peer: this.receivingList) {
            try {
				this.executorService.submit(new MessageListener(peer.getInputStream()));
			} catch (IOException e) {
                System.err.println("Unable to get peer " + peer.getLocalAddress().getCanonicalHostName() + "'s input stream");
				e.printStackTrace();
			}
        }
    }
}