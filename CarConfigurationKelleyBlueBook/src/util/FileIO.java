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

public class FileIO {

	// Read a file and store its attributes in an Automotive object
	public Automotive buildAutoObject(String fileName) throws AutoException {
		Fix1to100 fixer = new Fix1to100();
		Automotive automotive = null;

		try (BufferedReader buff = new BufferedReader(new FileReader(fileName))) {
			// Read make, model, and year from the file
			String makeModelYearLine = buff.readLine();
			if (makeModelYearLine == null) throw new AutoException("File is empty");
			String[] makeModelYearParts = makeModelYearLine.split(",");
			if (makeModelYearParts.length < 3) {
				throw new AutoException("Incomplete vehicle information in file.");
			}
			String make = makeModelYearParts[0].trim();
			String model = makeModelYearParts[1].trim();
			String year = makeModelYearParts[2].trim();

			// Read base price
			float basePrice;
			try {
				basePrice = Float.parseFloat(buff.readLine());
			} catch (NumberFormatException e) {
				System.out.println("There is no base price defined.");
				basePrice = (float) fixer.fix5(5); // Handle missing base price
			}

			// Initialize Automotive
			automotive = new Automotive(make, model, year, basePrice);

			// Read option set count
			int optionSetCount;
			try {
				optionSetCount = Integer.parseInt(buff.readLine());
			} catch (NumberFormatException e) {
				System.out.println("Error: Invalid option set count.");
				optionSetCount = (int) fixer.fix2(2); // Handle invalid option set count
			}

			// Process each OptionSet
			for (int i = 0; i < optionSetCount; i++) {
				String optionSetName = buff.readLine();
				automotive.addOptionSet(optionSetName);  // Ensure this method exists to add a new OptionSet by name

				// Read option count
				int optionCount;
				try {
					optionCount = Integer.parseInt(buff.readLine());
				} catch (NumberFormatException | NoSuchElementException e) {
					System.out.println("Error: Invalid option count.");
					optionCount = (int) fixer.fix3(3); // Handle invalid option count with fix3
				}

				// Process each Option within this OptionSet
				for (int j = 0; j < optionCount; j++) {
					String[] optionInfo = buff.readLine().split(",");
					if (optionInfo.length < 2) {
						System.out.println("Error: Incomplete option details.");
						continue; // Skip this iteration if the data is incomplete
					}
					String optionName = optionInfo[0].trim();
					float optionPrice;
					try {
						optionPrice = Float.parseFloat(optionInfo[1]);
					} catch (NumberFormatException e) {
						System.out.println("Error -- Please enter the price manually for " + optionInfo[0]);
						optionPrice = (float) fixer.fix1(1); // Handle missing price
					}

					automotive.updateOption(optionSetName, optionName, optionPrice);  // add and update operations
				}
			}
		} catch (IOException e) {
			System.out.println("Error -- " + e.toString());
			throw new AutoException("Failed to read file: " + fileName);
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