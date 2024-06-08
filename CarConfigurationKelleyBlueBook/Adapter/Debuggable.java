package Adapter;

public interface Debuggable {
	

	 boolean DEBUG = true; // Global debug flag

	    default void debug(String message) {
	        if (DEBUG) {
	            System.out.println(message);
}}}