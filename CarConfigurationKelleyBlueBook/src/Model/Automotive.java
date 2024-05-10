package Model;
import java.io.Serializable;
import java.util.ArrayList;
import Model.OptionSet.Option;

public class Automotive <Key,Value> implements Serializable {
	// private String name;
	private String make;
	private String model;
	private String year;
	private float basePrice;
	private ArrayList<OptionSet> optionSets;  // ArrayList to manage OptionSets
	private ArrayList <Option> choice;
	// Default constructor
	public Automotive() {
		this.optionSets = new ArrayList<>();
		this.choice = new ArrayList<>();
	}

	// Parameterized constructor
	public Automotive(String make, String model, String year, float basePrice) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.basePrice = basePrice;
		this.optionSets = new ArrayList<>();
		this.choice = new ArrayList<>();
	}

	//choice methods
	public String getOptionChoice(String name) {
		for (int i = 0; i < optionSets.size(); i++) {
			OptionSet option = optionSets.get(i);
			if (option.getSetName().equals(name)) {
				return choice.get(i).getOptionName();
			}
		}
		return null;
	}

	public float getOptionChoicePrice(String setName) {
		for (int i = 0; i < optionSets.size(); i++) {
			OptionSet o = optionSets.get(i);
			if (o.getSetName().equals(setName)) {
				return choice.get(i).getPrice();
			}
		}
		System.out.printf("Error! Option set was not found");
		return 0.0f;
	}
	public void setOptionChoice(String optionSetName, String optionName) {
		for (int i = 0; i < optionSets.size(); i++) {
			OptionSet ops = optionSets.get(i);
			if (ops.getSetName().equals(optionSetName)) {
				Option option = ops.findOption(optionName);
				// Check if the index exists in the choices list; if not, expand it.
				while (choice.size() <= i) {
					choice.add(null);  // Ensure the list is big enough
				}
				choice.set(i, option);  
				return;
			}
		}
	}
	public float getTotalPrice(){
		float price = 0.0f;
		for (int i = 0; i < choice.size(); i++)
			price += choice.get(i).getPrice();
		return price += this.basePrice;
	}


	//get methods

	public float getBasePrice() {
		return basePrice;
	}

	public ArrayList<OptionSet> getOptionSets() {
		return optionSets;
	}

	public OptionSet getOptionSet(int index) {
		if (index >= 0 && index < optionSets.size()) {
			return optionSets.get(index);
		}
		return null;
	}
	public String getMake() {
		return make;
	}
	public String getModel() {
		return model;
	}public String getYear() {
		return year;
	}
	// Setters
	public void setMake(String make) {
		this.make = make;
	}
	public void setModel(String model) {
		this.model = model;
	}public void setYear(String year) {
		this.year = year;
	}
	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}


	public void addOptionSet(String setName) {
		OptionSet newSet = new OptionSet(setName);
		optionSets.add(newSet);
		choice.add(null);  // Add a corresponding null entry to maintain index alignment
	}

	public void setOptionSet(int index, OptionSet set) {
		if (index >= 0 && index < optionSets.size()) {
			optionSets.set(index, set);
		}
	}

	// Methods to manipulate options within option sets
	public void updateOptionSet(String setName, int numOptions) {
		OptionSet set = new OptionSet(setName);
		for (int i = 0; i < numOptions; i++) {
			set.addOption("Option " + (i + 1), 0.0f);  // Default options
		}
		optionSets.add(set);
	}

	public void updateOption(String optionSetName, String optionName, float price) {
		for (OptionSet set : optionSets) {
			if (set.getSetName().equals(optionSetName)) {
				set.addOption(optionName, price);
				break;
			}
		}
	}

	public void updateOptionPrice(String optionName, float price) {
		for (OptionSet set : optionSets) {
			Option option = set.findOption(optionName);
			if (option != null) {
				option.setPrice(price);
				break;
			}
		}
	}
	public boolean updateOptionSetName(String oname, String nname) {
		int index = this.findOptionSet(oname);
		if (index != -1) {
			this.optionSets.get(index).setSetName(nname);
			return true;
		}
		return false;
	}

	public int findOptionSet(String name) {
		for (int i = 0; i < this.optionSets.size(); i++) {
			if (optionSets.get(i).getSetName().equals(name)) {
				return i;
			}
		}
		System.out.printf("\nOption Set not found!\n");
		return -1;
	}

	// printing 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Automotive Name: ").append(make).append(" ").append(model).append(" ").append(year).append("\nBase Price: $").append(basePrice);
		return sb.toString();
	}

	public void print() {
		System.out.println(this);
		for (OptionSet set : optionSets) {
			System.out.println("Option Set Name: " + set.getSetName());
			for (OptionSet.Option opt : set.getOptions()) {
				System.out.println("Option: " + opt.getOptionName() + ", Price: $" + opt.getPrice());
			}
		}
	}
}


