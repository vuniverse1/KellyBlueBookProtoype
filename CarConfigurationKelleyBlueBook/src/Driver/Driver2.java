package Driver;

import Adapter.BuildAuto;
import Adapter.CreateAuto;
import Adapter.UpdateAuto;

public class Driver2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreateAuto a1 = new BuildAuto();
		a1.buildAuto("/Users/jasonvu/Desktop/FordWagonZTW.txt"); //if cannot build auto, print error message, and fix 
		a1.printAuto("/Users/jasonvu/Desktop/FordWagonZTW.txt"); //once fixed, print

		
		UpdateAuto a2 = new BuildAuto();
		//change the text file name from colour to color
		a2.updateOptionSetName("Ford Wagon ZTW 2014", "Colour", "Color");
		//change the price of Matte paint because matte looks coolor and it is more expensive
		a2.updateOptionPrice("Ford Wagon ZTW 2014", "Columbia Blue Matte", 99.99f);
		//print the final updated and fixed automotive
		a1.printAuto("Ford Wagon ZTW 2014");
	}

}
