package server;

import Adapter.BuildAuto;

public class ServerDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port = 4445; // Set the server port
        BuildAuto server = new BuildAuto();
        server.serve(port);
	}

}
