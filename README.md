# p2p
Start 3 instance of Client

e.g
```
    java p2p.App 9801 5601
    java p2p.App 9802 5602
    java p2p.App 9803 5603
```

Start Server e.g `java Server` and enter following input on server

e.g
```
java Server
9801 5601
9802 5602
9803 5603
```

Now go to any one client console and write down any message and press enter. This message will be available to other 2 console. You can try this after closing peer discovery server also. This will work once server has informed all clients even server is closed