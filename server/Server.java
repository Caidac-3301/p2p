import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;;

public class Server {
    static List<Socket> clients = new ArrayList<>();
    static List<String> clientsAddress = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String peerInfo = bufferedReader.readLine();
            addClient(peerInfo.split(" "));
        }
    }

    public static void addClient(String... peerInfo) throws Exception {
        // Replace peerInfo with localhost for lan and fix port
        // For simulating in single machine peerInfo will be different port for different clients
        Socket clientSocket = new Socket("localhost", Integer.parseInt(peerInfo[1]));
        broadCastPeers(peerInfo[0]);
        broadCastTo(clientSocket);
        clients.add(clientSocket);
        clientsAddress.add(peerInfo[0]);
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