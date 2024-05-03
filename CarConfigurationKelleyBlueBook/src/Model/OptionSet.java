package Model;

import java.io.Serializable;

public class OptionSet implements Serializable {
	private String setName;
	private Option[] op;
	private int nextInsertPosition;

	public OptionSet() {
	}

	public OptionSet(String setName, int size) {
		this.setName = setName;
		op = new Option[size];
		nextInsertPosition = 0;
	}

	protected String getSetName() {
		return setName;
	}

	protected void setSetName(String setName) {
		this.setName = setName;
	}

	protected Option getOneOp(int i) {
		return op[i];
	}

	protected Option[] getOp() {
		return op;
	}

	protected void setOp(Option[] op) {
		this.op = op;
	}

	protected int getOpLength() {
		return op.length;
	}

	protected void buildOption(int x, String name, float price) {

	}

	protected String getOpName(int i) {
		return op[i].getName();
	}

	protected void setOpName(int x, String newOpName) {
		this.op[x].setName(newOpName);
	}

	protected float getOpPrice(int x) {
		return op[x].getPrice();
	}

	protected void setOpPrice(int x, float newOpPrice) {
		this.op[x].setPrice(newOpPrice);
	}

	// addOption() adds new Option to the OptionSet
	protected void addOption(Option option) {
		op[nextInsertPosition] = option;
		nextInsertPosition++;
	}

	// findOption() finds option by optionName in the Option set
	protected Option findOption(String optionName) {
		for (int i = 0; i < op.length; ++i) {
			if (op[i].getName().equalsIgnoreCase(optionName)) {
				op[i].print();
				return op[i];
			}
		}
		return null;
	}

	// deleteOption()
	protected boolean deleteOption(String OptionName) {
		Option foundOp = findOption(OptionName);
		// if option not found, return false
		if (foundOp == null)
			return false;

		Option[] newOptions = new Option[op.length - 1];
		for (int i = 0; i < op.length - 1; ++i) {
			if (op[i] != foundOp)
				newOptions[i] = op[i];
		}
		op = newOptions;
		return true;
	}

	protected void print() {
		System.out.println(toString());
	}

	protected void printOneOption(int x) {

	}

	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("OptionSet Name: ").append(setName);
		String str = stringBuffer.toString();
		return str;
	}

	public class Option implements Serializable {
		private String name;
		private float price;

		// constructors
		public Option() {
		}

		public Option(String name, float price) {
			this.name = name;
			this.price = price;
		}

		protected String getName() {
			return name;
		}

		protected void setName(String name) {
			this.name = name;
		}

		protected float getPrice() {
			return price;
		}

		protected void setPrice(float price) {
			this.price = price;
		}

		public String toString() {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("Option Name: ").append(name).append(", Price: $").append(price);
			String str = stringBuffer.toString();
			return str;
		}

		// print() prints Option object's attributes
		protected void print() {
			System.out.println(toString());
		}
	}
}
