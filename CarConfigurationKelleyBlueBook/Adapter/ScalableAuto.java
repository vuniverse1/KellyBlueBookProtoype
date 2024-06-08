package Adapter;

public interface ScalableAuto {
	
	Thread editThread(String make, String model, String year, int operation, String[] args);
	      
	}