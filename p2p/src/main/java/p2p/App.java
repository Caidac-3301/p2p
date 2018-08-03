package p2p;

import java.io.IOException;

import p2p.client.ClientImpl;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args) throws IOException
    {
        // first arg is port on which this client is listening and other clients can send data
        // second argument is port on which this client is listenenig for receiving new connectinos from peer doscpvery server
        // peer discovery server should send new client info on this port
        // Theses ports can be harcoded for lan. It's only for simulation of different clients in single machine in single 
        new ClientImpl(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        // new ClientImpl(9801, 5601, 9802, 9803);
    }
}
