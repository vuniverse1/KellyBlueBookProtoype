package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.NoSuchElementException;

import Model.Automotive;
import exception.AutoException;
import exception.Fix1to100;



    // read a file and store its attributes in Automotive object
	public class FileIO {

	    // read a file and store its attributes in Automotive object
	    public Automotive buildAutoObject(String fileName) throws AutoException {
	        Automotive automotive = null;
	        Fix1to100 fixer = new Fix1to100();
	        
	        while (true) {
	            int optionSetCount = 0;
	            int optionCount = 0;

	            try (BufferedReader buff = new BufferedReader(new FileReader(fileName))) {
	                String modelName = buff.readLine();
	                float basePrice;
	                
	                try {
	                basePrice = Float.parseFloat(buff.readLine());
	                }
	                catch(NumberFormatException e) { //fix issue if there is no base price
	                	System.out.printf("There is no base price defined.");
	                	basePrice = (Float) fixer.fix5(5);
	                }

	                // Attempt to read option set count
	                String optionSetCountStr = buff.readLine();
	                try {
	                    optionSetCount = Integer.parseInt(optionSetCountStr);
	                } catch (NumberFormatException e) {
	                    System.out.println("Error: Invalid option set count.");
	                    optionSetCount = (int) fixer.fix2(2); // fix2 fixes invalid option set count
	                }

	                automotive = new Automotive(modelName, basePrice, optionSetCount, optionCount);

	                // Process option sets and options
	                for (int i = 0; i < optionSetCount; i++) {
	                    String optionSetName = buff.readLine();
	                    try {
	                        optionCount = Integer.parseInt(buff.readLine());
	                    } catch (NumberFormatException e) {
	                        System.out.println("Error: Invalid option count.");
	                        optionCount = (int) fixer.fix3(3); // fix3 fixes invalid option count
	                    }
	                    catch (NoSuchElementException e) {
	                    	 System.out.println("Error: Invalid option count.");
		                        optionCount = (int) fixer.fix3(3); // fix3 fixes invalid option count
	                    }

	                    automotive.updateOptionSet(optionSetName, optionCount);

	                    for (int j = 0; j < optionCount; j++) {
	                        String[] optionInfo = buff.readLine().split(",");
	                        String optionName = optionInfo[0];
	                        float optionPrice;
	                        try {
	                            optionPrice = Float.parseFloat(optionInfo[1]);
	                        } catch (NumberFormatException e) {
	                            System.out.println("Error -- Please enter the price manually for " + optionInfo[0]);
	                            optionPrice = (Float) fixer.fix1(1); // fix1 number 1 corresponds to missing price
	                        }

	                        automotive.updateOption(optionSetName, optionName, optionPrice);
	                    }
	                }

	                // If all data is successfully read, break the loop
	                break;

	            } catch (IOException e) {
	                // Handle invalid file name error
	                System.out.println("Error -- " + e.toString());
	                fileName = (String) fixer.fix4(4);
	            }
	        }

	        return automotive;
	    
	}



	// serialize Automotive object in a file
	public void serializeAuto(Automotive automotive) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("auto.ser"))) {
			out.writeObject(automotive);
			System.out.println("Serialized data is saved in auto.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	// de-serialize Automotive object from given file
	public Automotive deserializeAuto(String fileName) {
		Automotive automotive = null;
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
			automotive = (Automotive) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return automotive;
	}

	
}