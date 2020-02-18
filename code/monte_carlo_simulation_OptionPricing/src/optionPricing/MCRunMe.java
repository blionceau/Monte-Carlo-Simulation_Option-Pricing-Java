package optionPricing;

/**
 * 
 * @author Simeon
 *
 *This class MCRunMe contains the main Method. 
 *Uncomment "simulate.callOptionPricing(); or comment simulate.asianCallOptionPricing();" 
 *depending on which type of payOut that you need to perform
 */
public class MCRunMe {

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		System.out.println("Running MC Simulation...");
		MonteCarloSimulation simulate = new MonteCarloSimulation();
		
		//--------------------------------------------------------------
		//Uncomment "simulate.callOptionPricing(); to run the callOptionPricing
		//--------------------------------------------------------------
		
		//simulate.callOptionPricing();
		simulate.asianCallOptionPricing();
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Runtime : " + totalTime + " Milli-seconds");
		
	}	
}
