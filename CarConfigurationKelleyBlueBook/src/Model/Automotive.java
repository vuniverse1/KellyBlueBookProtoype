package Model;

import java.io.Serializable;

import Model.OptionSet.Option;

public class Automotive implements Serializable { // class represents the model of the car
	private String name;
	private float basePrice;
	private OptionSet[] opset;
	private int nextInsertPosition;
	private Option[] op;
	private int optionSetSize;

	// default constructor
	public Automotive() {
	}

	// parameterized constructor
	public Automotive(String name, float basePrice, int optionSetSize, int optionSize) {
		this.name = name;
		this.basePrice = basePrice;
		opset = new OptionSet[optionSetSize];
		op = new Option[optionSize];
		nextInsertPosition = 0;
	}

	// getters
	public String getName() {
		return name;
	}

	public float getBasePrice() {
		return basePrice;
	}

	public OptionSet getOneOpset(int i) {
		return opset[i];
	}

	public OptionSet[] getOpset() {
		return opset;
	}

	public int getOpsetLength() {
		return opset.length;
	}

	public int getOpLength(int i) {
		return opset[i].getOpLength();
	}

	public String getOpsetName(int i) {
		return opset[i].getSetName();
	}

	public String getOpName(int x, int y) {
		return opset[x].getOpName(y);
	}

	public float getOpPrice(int x, int y) {
		return opset[x].getOpPrice(y);
	}

	// setters
	public void setName(String name) {
		this.name = name;
	}

	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	public void setOpset(OptionSet[] opset) {
		this.opset = opset;
	}

	public void setOneOpsetLength(OptionSet set, int i) {
		this.opset[i] = set;
	}

	public void setOpSetName(int i, String setName) {
		this.opset[i].setSetName(setName);
	}

	public void setOpName(int x, int y, String opName) {
		this.opset[x].setOpName(y, opName);
	}

	public void setOpPrice(int x, int y, float opPrice) {
		this.opset[x].setOpPrice(y, opPrice);
	}

	public void setOneOptionSetOption(int x, int y, String name, float price) {
		this.opset[x].buildOption(y, name, price);
	}
	public void setOpSetLength(int optionSetSize) {
		this.optionSetSize=optionSetSize;
	}
	
	
	
	
	
	// addOptionSet() adds optionSet in the optionSet array
	public void updateOptionSet(String str, int length) {
		OptionSet optset = new OptionSet(str, length);
		opset[nextInsertPosition] = optset;
		nextInsertPosition++;
	}

	public void updateOptionPrice(String optionname, float price) {
	    for (int i = 0; i < opset.length; i++) {
	        Option[] options = opset[i].getOp(); // Get the options for the current OptionSet
	        for (int j = 0; j < options.length; j++) {
	            if (options[j].getName().equals(optionname)) {
	                options[j].setPrice(price); // Update the price of the matching option
	                return; // Exit the loop once the option is found and updated
	            }
	        }
	    }
	}

	// addOption() adds option in the required OptionSet.
	public void updateOption(String optionSetName, String optionName, float price) {
		for (int i = 0; i < opset.length; ++i) {
			OptionSet.Option opt = opset[i].new Option();
			if (opset[i].getSetName() == optionSetName) {
				opt.setName(optionName);
				opt.setPrice(price);
				opset[i].addOption(opt);
				break;
			}
		}
	}

	// findOptionSet() searches for the required optionSet using optionSetName
	// and returns OptionSet object if found else null.
	public OptionSet findOptionSet(String optionSetName) {
		for (int i = 0; i < opset.length; ++i) {
			if (opset[i].getSetName().equalsIgnoreCase(optionSetName)) {
				opset[i].print();
				return opset[i];
			}
		}
		return null;
	}

	// findOption() searches for the option in the required optionSet using
	// optionName and returns option object if found else null.
	public OptionSet.Option findOption(String optionSetName, String optionName) {
		for (int i = 0; i < opset.length; ++i) {
			if (opset[i].getSetName().equalsIgnoreCase(optionSetName)) {
				return opset[i].findOption(optionName);
			}
		}
		return null;
	}

	// deleteOptionSet() deletes the required optionset from the option set
	// array
	public boolean deleteOptionSet(String optionSetName) {
		OptionSet foundOpSet = findOptionSet(optionSetName);
		if (foundOpSet == null)
			return false;

		OptionSet[] newOptset = new OptionSet[opset.length - 1];
		for (int j = 0; j < opset.length - 1; ++j) {
			if (opset[j] != foundOpSet) {
				newOptset[j] = opset[j];
			}
		}
		opset = newOptset;
		return true;
	}

	// deleteOption() deletes the required option from the optionSetArray
	public boolean deleteOption(String optionSetName, String optionName) {
		OptionSet opset = findOptionSet(optionSetName);
		if (opset != null) {
			return opset.deleteOption(optionName);
		}
		return false;
	}
	public boolean updateOptionSetName(String OptionSetName, String newOptionSetName) {
		OptionSet name = findOptionSet(OptionSetName);
		if (name != null) {
			name.setSetName(newOptionSetName);
			return true;
		}
		return false;
	}
	

	// toString() converts buffered string to a string
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Automotive Name: ").append(name).append("\nBase Price: $").append(basePrice);
		String str = stringBuffer.toString();
		return str;
	}

	// print() prints automotive object's attributes
	// print() prints automotive object's attributes
	public void print() {
		System.out.println(toString());
		for (int i = 0; i < opset.length; ++i) {
			opset[i].print(); // Print option set name
			for (int j = 0; j < opset[i].getOpLength(); ++j) {
				System.out.println(
						"Option " + (j + 1) + ": " + opset[i].getOpName(j) + ", Price: $" + opset[i].getOpPrice(j));
			}
		}
	}

}
