package Adapter;

public interface UpdateAuto {
	public void updateOptionSetName(String make, String model, String year, String oldOptionSetName,
			String newOptionSetName);

	public void updateOptionPrice(String make, String model, String year, String optionName, float newPrice);
}