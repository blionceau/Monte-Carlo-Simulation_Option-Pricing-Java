package optionPricing;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.joda.time.DateTime;
import org.joda.time.Period;

/**
 * 
 * @author Simeon 
 * Here in the MOnteCarloSimulation() method, the user can change inputs
 * (optionName, payOutType, initialPrice,initialTime, optionDuration, interestRate, volatility, strikePrice)
 *  
 *
 */

public class MonteCarloSimulation {

	private String optionName = "IBM";
	private double probability = 0.96;
	private double error = 0.01;
	private int numberOfDays = 252;
	private double initialPrice = 152.35;
	private double interestRate = 0.0001;
	private double volatility = 0.01;
	private String payOutType;
	private double strikePrice;
	private DateTime initialTime = new DateTime(2017, 10, 26, 10, 00);
	private Period optionDuration = Period.days(numberOfDays);
	private StatsCollector estValues = new StatsCollector();
	IStockPath paths;
	IPayOut payOut;

	/**
	 * Problem(1): The current market conditions are: the price of IBM is 152.35$, 
	 * the volatility sigma is 0.01 per day, and interest rates r = 0.0001 per day. 
	 * What is the price of a call option that ends 252 days into the future 
	 * and the strike price is 165$?
	 */
	public void callOptionPricing() {
		// estimated value 5.5 can be changed to a different number(double type)
		estValues.add(5.5);

		this.payOutType = "CallOption";
		// The strike price of the underlying asset
		this.strikePrice = 165;

		Option option = new Option(optionName, payOutType, initialPrice, initialTime, optionDuration, interestRate,
				volatility, strikePrice);

		IRandomVectorGenerator standardNormGenerator = new RandomVectorGeneratorImpl(option.getPeriod().getDays());
		IRandomVectorGenerator vectorGenerator = new AntitheticRandomVectorGeneratorImpl(standardNormGenerator);

		this.paths = new StockPathImpl(option, vectorGenerator);

		this.payOut = new CallOptionPayOutImpl(strikePrice);
		// The value of the Call option
		double value = MCSimulate(paths, payOut, estValues, probability, error);
		// The price of the Call option
		double price = value * Math.exp(-option.getInterestRate() * option.getPeriod().getDays());
		// Output display on the console
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("PayOut Type : " + payOutType);
		System.out.println("The value of " + optionName + " option is :" + "$" + value);
		System.out.println("The " + optionName + " option should be priced at :" + "$" + price);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("");
	}

	/**
	* Problem(2):  The current market conditions are: the price of IBM is 152.35$, the volatility sigma is 0.01 per day, 
	*and the interest rates r = 0.0001 per day. What is the price of an Asian call option that ends 252 days into 
	*the future and the strike price is 164$. Note an Asian call option will pay the maximum between zero 
	*and the average price during the 252 days minus the strike price.
	 */
	public void asianCallOptionPricing() {
		// estimated value 5.5 can be changed to a different number(double type)
		estValues.add(5.5);

		this.payOutType = "AsianCallOption";
		// // The strike price of the underlying asset
		this.strikePrice = 164;

		Option option = new Option(optionName, payOutType, initialPrice, initialTime, optionDuration, interestRate,
				volatility, strikePrice);

		IRandomVectorGenerator standardNormGenerator = new RandomVectorGeneratorImpl(option.getPeriod().getDays());
		IRandomVectorGenerator vectorGenerator = new AntitheticRandomVectorGeneratorImpl(standardNormGenerator);

		this.paths = new StockPathImpl(option, vectorGenerator);

		this.payOut = new AsianCallOptionPayOutImpl(strikePrice);
		// The value of the Asian Call option
		double value = MCSimulate(paths, payOut, estValues, probability, error);
		// The price of the Asian Call option
		double price = value * Math.exp(-option.getInterestRate() * option.getPeriod().getDays());
		// Output display on the console
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("PayOut Type : " + payOutType);
		System.out.println("The value of " + optionName + " option is :" + "$" + value);
		System.out.println("The " + optionName + " option should be priced at :" + "$" + price);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("");

	}

	public double MCSimulate(IStockPath paths, IPayOut payOut, StatsCollector estValues, double probability,
			double error) {
		// To calculate Find y such that P(|Y|<y)=99.6%, which is y=2.05....This can
		// help to find N number of simulations to perform.
		// y is calculated by using the formula for the inverse of the Normal
		// distribution.

		double Q_xp = -new NormalDistribution().inverseCumulativeProbability((1 - probability) / 2);

		// If the MC simulation does not converge based on our criteria, change the
		// upper bound of count(i.e.,10000000)

		for (int count = 1; count < 10000000; count++) {

			estValues.add(payOut.getPayout(paths));

			if (Q_xp * estValues.getStandardDev() / Math.sqrt(count) < error) {

				System.out.println(
						"----------------------------------MC simulation DONE!-------------------------------------");
				System.out.println("It requires " + count
						+ " number of simulations to converge below the threshold error of " + "$" + error);
				System.out.println(
						"------------------------------------------------------------------------------------------");
				break;

			}

		}

		return estValues.getMean();

	}

}
