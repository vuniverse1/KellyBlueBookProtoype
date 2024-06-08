package Adapter;

import java.util.Properties;

public interface CreateAuto {
	public void buildAuto(String fnmae);
	public void buildAutoFromProp(Properties prop);
	public void printAuto(String make, String model, String year);
}
