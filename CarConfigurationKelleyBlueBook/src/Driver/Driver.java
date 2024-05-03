package Driver;

/**
 * @author Jason Vu
 * Advanced Java Programming - CIS 35B 
 * Lab #1
 * 4-16-2024
 */

import Model.Automotive;
import util.FileIO;

public class Driver {
	public static void main(String[] args) {
		FileIO file = new FileIO();
		Automotive FordZTW = file.buildAutoObject("/Users/jasonvu/Desktop/FordWagonZTW.txt");

		System.out.println("Print attributes before serialization");
		FordZTW.print();

// serialize Automotive object
		file.serializeAuto(FordZTW);

// Deserialize Automotive object and read it into memory
		Automotive newFordXTW = file.deserializeAuto("auto.ser");
		System.out.println("\nNew Automotive attributes are:");
		newFordXTW.print();

	}
}