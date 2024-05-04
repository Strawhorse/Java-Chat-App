# Java-Chat-App
 Java chat app built in Java 17 with simple Swing interface

Background info needed for project understanding:

1. Sockets: endpoints for bidirectional communication between 2 networked programmes. Need specific port number. Allow TCP protocol to determine recipient
Java has a Socket class which represents a socket
Two types of socket: ServerSocket - listen to incoming network requests, execute actions; Socket - communication endpoints between two applications
2. Setup: (a) Server - Create a ServerSocket and give it a port to monitor. Use accept() method to await a client connection. After connecting to a client, the server can interact with client using the above Socket object that the accept() method provides.
   (b) Client - also initialise a Socket object with the server hostname and port number to connect. Client can use this Socket object to communicate with the server.
   (c) Front end




Good sources:
Sockets and output streams: https://www.javatpoint.com/java-socket-getoutputstream-method
Finally keyword in try/catch block: https://www.javatpoint.com/finally-block-in-exception-handling#:~:text=Java%20finally%20block%20is%20a,the%20exception%20occurs%20or%20not.

Finding all the properties of a class: https://stackoverflow.com/questions/50081856/how-can-i-get-class-parameters-of-class-file


Next to do:

Add a Constructor Parameter: Modify the constructor to accept a Consumer<String> parameter. This consumer will be called with incoming messages from the server.
Handle Incoming Messages: Inside the startClient method, read messages from the server in a loop. For each message received, call the consumer passed in the constructor.

Currently here: https://hackr.io/blog/how-to-build-a-java-chat-app#step-6-enhancing-the-chat-application-optional

Bug-hunting


Update:
Created a new package under com.Jbr.networkChat to test out some networking protocols on

