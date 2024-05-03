package Adapter;

import Model.Automotive;
import util.FileIO;
import exception.AutoException;
import exception.AutoException.ErrorType;
import exception.Fix1to100;

public abstract class proxyAutomobile {
    private static Automotive a1;

    public void updateOptionSetName(String modelName, String optionSetName, String newOptionSetName) {
        if (a1 != null) {
            a1.updateOptionSetName(optionSetName, newOptionSetName);
        } else {
            System.out.println("Automotive object is not initialized. Please build the Automotive object first.");
            // Or you can throw an exception if needed
            // throw new IllegalStateException("Automotive object is not initialized.");
        }
    }

    public void updateOptionPrice(String modelName, String optionName, float newPrice) {
        if (a1 != null) {
            a1.updateOptionPrice(optionName, newPrice);
        } else {
            System.out.println("Automotive object is not initialized. Please build the Automotive object first.");
          
        }
    }


    public void buildAuto(String fname) {
        FileIO f = new FileIO();
        Automotive temp = null;
        try {
            temp = f.buildAutoObject(fname);
        } catch (AutoException e) {
            e.printStackTrace();
            AutoException autoEx = new AutoException(ErrorType.MISSING_PRICE);
            fix(autoEx.getErrorno()); // Call fix method to handle exception
            
            
        }
        
        if (temp != null) {
            a1 = temp; // Assign temp to a1
        } else {
            System.out.println("Failed to build Automotive object from file: " + fname);
        }
    }


    public void printAuto(String modelName) {
        if (a1 != null) {
            a1.print(); // Print the Automotive object
        } else {
            System.out.println("Automotive object is not initialized. ");
        }
    }
    public void fix(int errorno) {
		Fix1to100 f1 = new Fix1to100();

		switch (errorno) {
		case 1:
			 f1.fix1(errorno);
			 break;
		case 2:
			f1.fix2(errorno);
			break;
		case 3:
			f1.fix3(errorno);
			break;
		case 4:
			f1.fix4(errorno);
			 break;
		case 5:
			 f1.fix5(errorno);
			 break;
		}
		
	}
    
}

    
