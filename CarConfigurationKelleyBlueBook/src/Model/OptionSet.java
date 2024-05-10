package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class OptionSet implements Serializable {
    private String setName;
    private ArrayList<Option> options;  // ArrayList to manage Option objects dynamically

    public OptionSet() {
        this.options = new ArrayList<>();
    }

    public OptionSet(String setName) {
        this.setName = setName;
        this.options = new ArrayList<>();
    }

    // Accessor methods
    protected String getSetName() {
        return setName;
    }

    protected void setSetName(String setName) {
        this.setName = setName;
    }

    protected ArrayList<Option> getOptions() {
        return options;
    }

    protected Option getOption(int index) {
        if (index >= 0 && index < options.size()) {
            return options.get(index);
        }
        return null;  // Return null if index is out of bounds
    }

    protected void addOption(String name, float price) {
        options.add(new Option(name, price));
    }

    protected boolean deleteOption(String optionName) {
        return options.removeIf(option -> option.getOptionName().equalsIgnoreCase(optionName));
    }

    protected Option findOption(String optionName) {
        for (Option option : options) {
            if (option.getOptionName().equalsIgnoreCase(optionName)) {
                return option;
            }
        }
        return null;
    }
    protected Option getOptionChoice(int i) {
		return this.options.get(i);
	}

	protected void setOptionChoice(int i, String optionName) {
		this.options.get(i).setOptionName(optionName);
	}

    protected void updateOptionName(int index, String newName) {
        if (index >= 0 && index < options.size()) {
            options.get(index).setOptionName(newName);
        }
    }

    protected void updateOptionPrice(int index, float newPrice) {
        if (index >= 0 && index < options.size()) {
            options.get(index).setPrice(newPrice);
        }
    }

    // Printing and toString methods
    public void print() {
        System.out.println("OptionSet Name: " + setName);
        for (Option option : options) {
            option.print();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("OptionSet Name: ").append(setName);
        for (Option option : options) {
            sb.append("\n").append(option);
        }
        return sb.toString();
    }

    public class Option implements Serializable {
        private String name;
        private float price;

        public Option() {
        }

        public Option(String name, float price) {
            this.name = name;
            this.price = price;
        }

        protected String getOptionName() {
            return name;
        }

        protected void setOptionName(String name) {
            this.name = name;
        }

        protected float getPrice() {
            return price;
        }

        protected void setPrice(float price) {
            this.price = price;
        }

        public String toString() {
            return "Option Name: " + name + ", Price: $" + price;
        }

        protected void print() {
            System.out.println(toString());
        }
    }
}

