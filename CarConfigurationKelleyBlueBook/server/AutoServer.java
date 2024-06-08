package server;

import java.util.Properties;

public interface AutoServer {
	public void serve(int port);
	public void addAutoToLHM(Properties out);
	public String getAllModels();
}
