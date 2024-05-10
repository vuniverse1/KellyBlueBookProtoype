package Adapter;

import java.util.LinkedHashMap;

import Model.Automotive;
import util.FileIO;
import exception.AutoException;
import exception.AutoException.ErrorType;
import exception.Fix1to100;

public abstract class proxyAutomobile {
	//private static Automotive a1;
	private static LinkedHashMap<String, Automotive> a1 = new LinkedHashMap<String, Automotive>();

	public void updateOptionSetName(String make, String model, String year, String optionsetname, String newnmae) {
		a1.get(make + model + year).updateOptionSetName(optionsetname, newnmae);
	}

	public void updateOptionPrice(String make, String model, String year, String optionname, float newprice) {
		a1.get(make + model + year).updateOptionPrice(optionname, newprice);
	}

	public void buildAuto(String fname) {
		FileIO f = new FileIO();
		Automotive temp = null;
		try {
			temp = f.buildAutoObject(fname);
		} catch (AutoException e) {
			e.printStackTrace();
		}
		String key = temp.getMake() + temp.getModel() + temp.getYear();
		a1.put(key, temp);

	}


	public void printAuto(String make, String model, String year) {
		a1.get(make + model + year).print();
	}



	public void chooseOption(String make, String model, String year, String optionset, String option) {
		a1.get(make + model + year).setOptionChoice(optionset, option);
	}

	public void printTotalPrice(String make, String model, String year) {
		System.out.printf("TOTAL PRICE: $%.2f!\n", a1.get(make + model + year).getTotalPrice());
		System.out.printf("Thank you for purchasing The %s %s %s!", year, make, model);
	}

	public void printOptionChoice(String make, String model, String year, String optionSet) {
		System.out.printf("You choose %s %s\n", a1.get(make + model + year).getOptionChoice(optionSet),
				optionSet);
	}

	public void printOptionPrice(String make, String model, String year, String option) {
		System.out.printf("The %s is $%.2f.\n", option,
				a1.get(make + model + year).getOptionChoicePrice(option));
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



