package server;

import java.io.IOException;
import java.util.Properties;

import Adapter.*;
import Model.Automotive;
import Model.OptionSet;
import Model.OptionSet.Option;
import util.FileIO;


public class BuildCarModelOptions extends proxyAutomobile implements AutoServer{

	////////// PROPERTIES //////////

	private static final int WAITING = 0;
	private static final int REQUEST_BUILD_AUTO = 1;
	private static final int REQUEST_CONFIGURE_AUTO = 2;
	private static final int REQUEST_LIST_AUTO = 3;


	private int state = WAITING;

	////////// CONSTRUCTORS //////////

	public BuildCarModelOptions() {

	}

	////////// INSTANCE METHODS //////////

	public Object processRequest(Object obj) {
	    Object toClient = null;

	    switch (state) {
	        case REQUEST_BUILD_AUTO:
	            Properties props = (Properties) obj;
	            addAutoToLHM(props);
	            toClient = "Automobile object successfully added to database\n" +
	                       "Press any key to return to main menu";
	            break;
	        case REQUEST_CONFIGURE_AUTO:
	            String modelKey = (String) obj;
	            Automotive auto = getAuto(modelKey); // Ensure this returns Automotive
	            if (auto != null) {
	                // Create a formatted response containing base price and options
	                StringBuilder response = new StringBuilder();
	                response.append("\nAutomotive Name: ").append(auto.getName()).append("\n");
	                response.append("Base Price: $").append(auto.getBasePrice()).append("\n");
	                for (OptionSet optionSet : auto.getOptionSets()) {
	                    response.append(optionSet.getSetName()).append(":");
	                    for (Option option : optionSet.getOptions()) {
	                        response.append(option.getOptionName()).append("=").append(option.getPrice()).append(",");
	                    }
	                    response.deleteCharAt(response.length() - 1); // Remove trailing comma
	                    response.append("\n");
	                }
	                toClient = response.toString();
	            } else {
	                toClient = "No such model found.";
	            }
	            break;
	        case REQUEST_LIST_AUTO:
	            toClient = super.getAllModels();  // Assuming getAllModels returns all model names as a string
	            break;
	        default:
	            toClient = "Invalid request.";
	            break;
	    }

	    this.state = WAITING;  // Reset state to waiting after processing
	    return toClient;
	}



	public String setRequest(int i) {
	    String output = null;

	    switch (i) {
	        case 1:
	            this.state = REQUEST_BUILD_AUTO;
	            output = "Upload a file to create an Automobile";
	            break;
	        case 2:
	            this.state = REQUEST_CONFIGURE_AUTO;
	            output = "Select an Automobile from the following list to configure: \n" +
	                     super.getAllModels();
	            break;
	        case 3:
	            this.state = REQUEST_LIST_AUTO;
	            output = "Request received to list all Automobiles";
	            break;
	        default:
	            output = "Invalid request";
	            break;
	    }

	    return output;
	}
}

	
   
