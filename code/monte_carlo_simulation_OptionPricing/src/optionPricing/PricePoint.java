package optionPricing;

import org.joda.time.DateTime;
/**
 * 
 * @author Simeon
 * PricePoint class tracks the date-time of the option and its corresponding price.
 *
 */
public class PricePoint {

	private DateTime time;
	private double price;

	public PricePoint(DateTime time, double price) {

		this.time = time;
		this.price = price;

	}
	/**
	 * 
	 * @return time
	 */
	public DateTime getTime() {
		return time;
	}

	public void setTime(DateTime time) {
		this.time = time;
	}
	/**
	 * 
	 * @return price
	 */
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
