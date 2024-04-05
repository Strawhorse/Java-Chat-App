# Java-Chat-App
 Java chat app built in Java 17 with simple Swing interface

Background info needed for project understanding:

1. Sockets: endpoints for bi-directional communication between 2 networked programmes. Need specific port number. Allow TCP protocol to determine recipient
Java has a Socket class which represents a socket
Two types of socket: ServerSocket - listen to incoming network requests, execute actions; Socket - communication endpoints between two applications
2. Setup: (a) Server - Create a ServerSocket and give it a port to monitor. Use accept() method to await a client connection. After connecting to a client, the server can interact with client using the above Socket object that the accept() method provides.
   (b) Client - also initialise a Socket object with the server hostname and port number to connect. Client can use this Socket object to communicate with the server.
3. TBC

