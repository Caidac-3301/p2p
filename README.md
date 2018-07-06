# p2p
A peer discovery and update server for p2p apps.

👶🏻 🚧 under heavy construction 🚧 👶🏻

Right now the implementation is very basic. Server just echos a list of current clients to a new client. 
It is blocking and single threaded. Starts up on a random port.

You can test it by using TCP clients from different hosts and sending a single string which will be used as the name of the
client. The server should echo back a list all the registered clients with their hostnames(v4 ip representations as fallback).

Don't use REST clients as their header info will mess up the name. Currently, the server is only reading a single line from
the socket stream for the name(will explain the reason for it in an issue later).
