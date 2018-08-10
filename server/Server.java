import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;;

public class Server {
    static List<Socket> clients = new ArrayList<>();
    static List<String> clientsAddress = new ArrayList<>();

    public static final int PEER_DISCOVERY_PORT = 5601;

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String peerIp = bufferedReader.readLine();
            addClient(peerIp);
        }
    }

    public static void addClient(String peerIp) throws Exception {
        Socket clientSocket = new Socket(peerIp, PEER_DISCOVERY_PORT);
        broadCastPeers(peerIp);
        broadCastTo(clientSocket);
        clients.add(clientSocket);
        clientsAddress.add(peerIp);
    }

    public static void broadCastTo(Socket newClient) throws Exception {
        DataOutputStream dataOutputStream = new DataOutputStream(newClient.getOutputStream());
        for (String clientAddress : clientsAddress)
            dataOutputStream.writeUTF(clientAddress);
    }

    public static void broadCastPeers(String peerInfo) throws Exception {
        for (Socket client : clients)
            new DataOutputStream(client.getOutputStream()).writeUTF(peerInfo);
    }
}