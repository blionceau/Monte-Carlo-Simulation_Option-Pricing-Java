package optionPricing;

import org.joda.time.DateTime;
import org.joda.time.Period;

/**
 * 
 * @author Simeon
 *Option class contains setters and getters and inputs(i.e.,String name, String payOutType, double initialPrice, DateTime initialTime, Period period,
			double interestRate, double volatility, double strikePrice) of the option,
 */
public class Option {

	private String payOutType;
	private String name;
	private double initialPrice;
	private DateTime initialTime;
	private double interestRate;
	private double volatility;
	private double strikePrice;
	private Period period;


	public Option(String name, String payOutType, double initialPrice, DateTime initialTime, Period period,
			double interestRate, double volatility, double strikePrice) {

		this.setInitialPrice(initialPrice);
		this.setInitialTime(initialTime);
		this.setInterestRate(interestRate);
		this.setVolatility(volatility);
		this.setStrikePrice(strikePrice);
		this.setPeriod(period);
		this.setName(name);
		this.setPayOutType(payOutType);

	}



	public double getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(double initialPrice) {
		this.initialPrice = initialPrice;
	}

	public DateTime getInitialTime() {
		return initialTime;
	}
	
	public void setInitialTime(DateTime initialTime) {
		this.initialTime = initialTime;
	}

	public double getInterestRate() {
		return interestRate;

	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;

	}

	public double getVolatility() {
		return volatility;

	}

	public void setVolatility(double volatility) {
		this.volatility = volatility;

	}

	public double getStrikePrice() {
		return strikePrice;
	}

	public void setStrikePrice(double strikePrice) {
		this.strikePrice = strikePrice;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;

	}

	public String getName() {
		return name;

	}

	public void setName(String optionName) {
		this.name = optionName;

	}

	public String getPayOutType() {
		return payOutType;

	}

	public void setPayOutType(String payOutType) {
		this.payOutType = payOutType;

	}

}
