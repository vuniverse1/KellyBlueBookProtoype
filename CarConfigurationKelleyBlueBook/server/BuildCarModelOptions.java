package server;

import java.io.IOException;
import java.util.Properties;

import Adapter.*;
import Model.Automotive;
import util.FileIO;

public class BuildCarModelOptions extends proxyAutomobile implements AutoServer{

	////////// PROPERTIES //////////

	private static final int WAITING = 0;
	private static final int REQUEST_BUILD_AUTO = 1;
	private static final int REQUEST_CONFIGURE_AUTO = 2;

	private int state = WAITING;

	////////// CONSTRUCTORS //////////

	public BuildCarModelOptions() {

	}

	////////// INSTANCE METHODS //////////

	public Object processRequest(Object obj) {
	    Object toClient = null;

	    if (state == REQUEST_BUILD_AUTO) {
	        // Add code to build auto
	        Properties props = (Properties) obj;
	        addAutoToLHM(props);

	        toClient = "Automobile object successfully added to database\n" +
	                   "Press any key to return to main menu";
	    }
	    else if (state == REQUEST_CONFIGURE_AUTO) {
	        // Add code for configure auto
	        String modelKey = (String) obj;
	        Automotive auto = getAuto(modelKey);

	        if (auto != null) {
	            toClient = auto;
	        } else {
	            toClient = "No such model found.";
	        }
	    }
	    else {
	        toClient = "Invalid request.";
	    }

	    this.state = WAITING;

	    return toClient;
	}


	public String setRequest(int i) {
		String output = null;

		if (i == 1) {
			this.state = REQUEST_BUILD_AUTO;
			output = "Upload a file to create an Automobile";
		}
		else if (i == 2) {
			this.state = REQUEST_CONFIGURE_AUTO;
			output = "Select an Automobile from the following list to configure: \n" +
					super.getAllModels();
		}
		else {
			output = "Invalid request";
		}

		return output;
	}}

	
   
