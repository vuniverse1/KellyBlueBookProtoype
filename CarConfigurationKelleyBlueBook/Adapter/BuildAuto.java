package Adapter;

import server.AutoServer;

public class BuildAuto 
	extends proxyAutomobile implements CreateAuto, UpdateAuto, FixAuto, ChooseOptions, ScalableAuto, AutoServer
{

	
}
//javac server/*.java client/*.java Model/*.java Adapter/*.java util/*.java scale/*.java exception/*.java
// /Users/jasonvu/Desktop/FordWagonZTWProperties.properties