package client;

import Model.Automotive;
import java.util.Scanner;

public class SelectCarOptions {

    ////////// PROPERTIES //////////
    private Scanner in = new Scanner(System.in);

    ////////// CONSTRUCTORS //////////

    public SelectCarOptions() {
    }

    ////////// INSTANCE METHODS //////////

    public void configureAuto(Object obj) {
        if (!isAutomobile(obj)) {
            System.out.println("Object is not an instance of Automobile.");
            return;
        }

        Automotive auto = (Automotive) obj;
        System.out.println("Configuring Automobile: " + auto.getMake() + " " + auto.getModel() + " " + auto.getYear());

        // Loop through each option set and prompt the user to select an option
        for (int i = 0; i < auto.getOptionSetCount(); i++) {
            String optionSetName = auto.getOptionSetName(i);
            System.out.println("Option Set: " + optionSetName);
            auto.printOptions(i);

            System.out.print("Choose an option: ");
            String optionName = in.nextLine();

            // Set the option choice in the automotive object
            auto.setOptionChoice(optionSetName, optionName);
        }

        // Display the final configuration and total price
        System.out.println("Configuration Complete. Selected options:");
        for (int i = 0; i < auto.getOptionSetCount(); i++) {
            String optionSetName = auto.getOptionSetName(i);
            String optionName = auto.getOptionChoice(optionSetName);
            float optionPrice = auto.getOptionChoicePrice(optionSetName);
            System.out.printf("Option Set: %s, Choice: %s, Price: $%.2f\n", optionSetName, optionName, optionPrice);
        }
        System.out.printf("Total Price: $%.2f\n", auto.getTotalPrice());
    }

    public boolean isAutomobile(Object obj) {
        boolean isAutomobile = false;
        try {
            Automotive a1 = (Automotive) obj;
            isAutomobile = true;
        } catch (ClassCastException e) {
            isAutomobile = false;
        }
        return isAutomobile;
    }
}

