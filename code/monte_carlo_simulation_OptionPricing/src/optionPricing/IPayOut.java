package optionPricing;
/**
 * 
 * @author Simeon
 *  The interface IPayOut for calculating the payout function
 */
public interface IPayOut {
	public double getPayout(IStockPath path);

}
