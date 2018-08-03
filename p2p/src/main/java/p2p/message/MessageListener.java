package p2p.message;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MessageListener implements Runnable {

    private DataInputStream dataInputStream;
    StringBuilder message = new StringBuilder();

    public MessageListener(InputStream inputStream) {
        this.dataInputStream = new DataInputStream(inputStream);
    }

	@Override
	public void run() {
		while (true) {
            try {
                byte[] data = new byte[2];
                this.dataInputStream.read(data);
                System.out.print(new String(data));

                if (data[0] == 0) break;
			} catch (IOException e) {
				System.err.println("Error while reading incoming message");
                e.printStackTrace();
                break;
			}
        }
	}
}