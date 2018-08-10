package p2p.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class PeerListener implements Runnable {

    private DataInputStream dataInputStream;
    List<Socket> sendingList;

    public PeerListener(Socket incomingPeerSocket, List<Socket> sendingList) throws IOException {
        this.dataInputStream = new DataInputStream(incomingPeerSocket.getInputStream());
        this.sendingList = sendingList;
    }

	@Override
	public void run() {
		while (true) {
            try {
                String incomingPeer = dataInputStream.readUTF();
                Socket peer = new Socket(incomingPeer, ClientImpl.PORT);
                this.sendingList.add(peer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
                // e.printStackTrace();
                break;
			}
        }
	}

}