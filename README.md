# p2p
Start instances of Client on different machine

e.g
```
    java p2p.App
```

Start Server e.g `java Server` on any machine and enter ip of clients

e.g
```
    java Server
    192.168.xxx.yyy
    192.168.xxx.abc
```

Now go to any one client console and write down any message and press enter. This message will be available to other peers. You can try this after closing peer discovery server also. This will work once server has informed all clients even server is closed