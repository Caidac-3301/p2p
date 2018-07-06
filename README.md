# p2p
A peer discovery and update server for p2p apps.

👶🏻 🚧 under heavy construction 🚧 👶🏻

Right now the implementation is very basic.

1. When a TCP request comes with a string body, the server will read the first line of that string and consider that as a name
for that client. The server now creates a client by that name and store it.

2. The server now just echo back a list of all the currently registered clients back to the client.

It is blocking, single threaded and starts up on a random port.

You can test it by using 2 TCP clients from different hosts and sending a request with a string name from each one. The server should echo back a list all the registered clients(two in this case) with their hostnames(v4 ip representations as fallback).

Don't use REST clients as their header info will mess up the name. Currently, the server is only reading a single line from
the socket stream for the name(will explain the reason for it in an issue later).
