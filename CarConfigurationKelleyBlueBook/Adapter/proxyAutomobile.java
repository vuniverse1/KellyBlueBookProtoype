package Adapter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Properties;

import Model.Automotive;
import util.FileIO;
import exception.AutoException;
import exception.Fix1to100;
import scale.EditOptions;
import server.DefaultServerSocket;

public abstract class proxyAutomobile {
    protected static LinkedHashMap<String, Automotive> a1 = new LinkedHashMap<>();

    public void updateOptionSetName(String make, String model, String year, String optionsetname, String newname) {
        a1.get(make + model + year).updateOptionSetName(optionsetname, newname);
    }

    public void updateOptionPrice(String make, String model, String year, String optionname, float newprice) {
        a1.get(make + model + year).updateOptionPrice(optionname, newprice);
    }
    public Automotive getAuto(String s) {
		return a1.get(s);
	}
    public void buildAuto(String fname) {
        FileIO f = new FileIO();
        Automotive temp = null;
        try {
            temp = f.buildAutoObject(fname);
        } catch (AutoException e) {
            e.printStackTrace();
        }
        String key = temp.getMake() +" "+ temp.getModel() +" "+ temp.getYear();
        a1.put(key, temp);
    }

    public void buildAutoFromProp(Properties prop) {
    	FileIO f = new FileIO();
    	
    }
    
    public void addAutoToLHM(Properties props) {
        FileIO fileIO = new FileIO();
        Automotive auto = null;
		try {
			auto = fileIO.loadAutoFromProperties(props);
			String key = auto.getMake() + " "+ auto.getModel() +" "+ auto.getYear();
			a1.put(key, auto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

    public String getAllModels() {
        StringBuilder models = new StringBuilder();
        for (String key : a1.keySet()) {
            models.append(key).append("\n");
        }
        return models.toString();
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
        System.out.printf("You chose %s %s\n", a1.get(make + model + year).getOptionChoice(optionSet), optionSet);
    }

    public void printOptionPrice(String make, String model, String year, String option) {
        System.out.printf("The %s is $%.2f.\n", option, a1.get(make + model + year).getOptionChoicePrice(option));
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

    public void serve(int port) {
        DefaultServerSocket server = new DefaultServerSocket(port);
        server.start();
    }
    public Thread editThread(String make, String model, String year, int operation, String[] args) {
        String key = make + model + year;
        EditOptions editOptions = new EditOptions(key, operation, args);
        Thread t = new Thread(editOptions);
        t.start();
        return t;
    }

}

