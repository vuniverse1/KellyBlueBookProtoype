package client;

public class ClientDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String serverAddress = "127.0.0.1"; 
        int port = 4445; 
        
        DefaultSocketClient client = new DefaultSocketClient(serverAddress, port);
        client.start();
	}

}
