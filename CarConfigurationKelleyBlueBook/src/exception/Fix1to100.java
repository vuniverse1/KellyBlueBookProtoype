package exception;
import java.io.File;
import java.util.Scanner;

import Model.Automotive;

public class Fix1to100 {


	public Object fix1(int errorno) {
        // Missing price for Automobile's options in text file
        
		System.out.printf("\nPlease enter the price: ");
		Scanner key = new Scanner(System.in);
		float f = key.nextFloat();
		
		
		return f;
    }

    public Object fix2(int errorno) {
        // Missing OptionSet data (or part of it)
        
    	System.out.printf("\nEnter the correct option set size:");
		Scanner key = new Scanner(System.in);
		int size = key.nextInt();
		
		return size;
    }

    public Object fix3(int errorno) {
    	// Missing Option data
     
        System.out.printf("\nPlease enter the Option count:");
        Scanner key = new Scanner(System.in);
        int optionCount = key.nextInt();
        
        return optionCount;
    }

    public Object fix4(int errorno) {
        // Missing filename or wrong filename
        System.out.printf("\nThere was an error with the filename or the filename is missing.\n");
        System.out.printf("Please enter the correct file name: ");

        Scanner key = new Scanner(System.in);
        String filename = "/Users/jasonvu/Desktop/" + key.nextLine().trim(); // Trim to remove leading/trailing whitespace
        

        // Validate filename
        File file = new File(filename);
        if (!file.exists()) {
            System.out.printf("Error: The file does not exist.");
            return null;
        }

        return filename;
    }
    public Object fix5(int errorno) {
    	//missing base price

		System.out.printf("\nPlease enter the automotive's base price: ");
		Scanner key = new Scanner(System.in);
		float f = key.nextFloat();
		
		
		return f;
    }

   
}
