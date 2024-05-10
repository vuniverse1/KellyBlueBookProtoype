package Driver;

/**
 * @author Jason Vu 20533501
 * 5-7-2024
 * Bob Singh Advanced Java 35b

 **/
import Adapter.BuildAuto;
import Adapter.ChooseOptions;
import Adapter.CreateAuto;
import Adapter.UpdateAuto;
import exception.AutoException;

public class Driver3 {

	public static void main(String[] args) throws AutoException {
		
		CreateAuto a1 = new BuildAuto();
		a1.buildAuto("/Users/jasonvu/Desktop/FordWagonZTW.txt"); //if cannot build auto, print error message, and fix 
		//a1.printAuto("Ford", "Focus Wagon ZTW", "2014"); 

		UpdateAuto a2 = new BuildAuto();
		
		a2.updateOptionSetName("Ford", "Focus Wagon ZTW", "2014", "Colour", "Color");
		a2.updateOptionPrice("Ford", "Focus Wagon ZTW", "2014", "Columbia Blue Matte", 500.00f);
		a1.printAuto("Ford", "Focus Wagon ZTW", "2014");
		
		System.out.printf("\n");
		ChooseOptions a3 = new BuildAuto();
		a3.chooseOption("Ford", "Focus Wagon ZTW", "2014", "Color", "Columbia Blue Matte");
		a3.printOptionChoice("Ford", "Focus Wagon ZTW", "2014", "Color");
		a3.printOptionPrice("Ford", "Focus Wagon ZTW", "2014", "Color");

		a3.chooseOption("Ford", "Focus Wagon ZTW", "2014", "Transmission", "automatic");
		a3.printOptionChoice("Ford", "Focus Wagon ZTW", "2014", "Transmission");
		a3.printOptionPrice("Ford", "Focus Wagon ZTW", "2014", "Transmission");

		a3.chooseOption("Ford", "Focus Wagon ZTW", "2014", "Brakes", "ABS with Advance Traction");
		a3.printOptionChoice("Ford", "Focus Wagon ZTW", "2014", "Brakes");
		a3.printOptionPrice("Ford", "Focus Wagon ZTW", "2014", "Brakes");

		a3.chooseOption("Ford", "Focus Wagon ZTW", "2014", "Side Impact Airbag", "Included");
		a3.printOptionChoice("Ford", "Focus Wagon ZTW", "2014", "Side Impact Airbag");
		a3.printOptionPrice("Ford", "Focus Wagon ZTW", "2014", "Side Impact Airbag");

		a3.chooseOption("Ford", "Focus Wagon ZTW", "2014", "Power Moonroof", "Included");
		a3.printOptionChoice("Ford", "Focus Wagon ZTW", "2014", "Power Moonroof");
		a3.printOptionPrice("Ford", "Focus Wagon ZTW", "2014", "Power Moonroof");

		a3.printTotalPrice("Ford", "Focus Wagon ZTW", "2014");
		
	}

}
