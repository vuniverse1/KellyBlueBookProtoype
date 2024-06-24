package server;

import Adapter.BuildAuto;

/**
 * @author Jason Vu 20533501
 * run server driver first, then client driver in separate terminal window
 * cd eclipse-workspace/CarConfigurationProject/src
 *  javac server/*.java client/*.java Model/*.java Adapter/*.java util/*.java scale/*.java exception/*.java
 *  /Users/jasonvu/Desktop/FordWagonZTWProperties.properties
 *   /Users/jasonvu/Desktop/Cybertruck.properties
 */ 

public class ServerDriver {

    public static void main(String[] args) {
        // Start the existing server socket
        int port = 5555; // Set the server port
        BuildAuto server = new BuildAuto();
        server.serve(port);
     System.out.println("Server is running on port: " + port);
        
    }
}
