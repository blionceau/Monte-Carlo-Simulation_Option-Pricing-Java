package optionPricing;

import java.util.List;

/**
 * 
 * @author Simeon
 * @see IPayOut
 *AsianCallOptionPayOutImpl implements the IPayOut Interface
 * Call pay-out: max(Avg(S)-K)+
 * 
 */

public class AsianCallOptionPayOutImpl implements IPayOut {

	private double strikePrice;

	public AsianCallOptionPayOutImpl(double strikePrice) {

		this.strikePrice = strikePrice;

	}
/**
 * 
 * @param option
 * @return max(Avg(S)-K)+
 * @return strikePrice
 * 
 */
	public AsianCallOptionPayOutImpl(Option option) {

		this.strikePrice = option.getStrikePrice();

	}

	public double getStrikePrice() {

		return strikePrice;

	}

	public void setStrikePrice(double strikePrice) {

		this.strikePrice = strikePrice;

	}

	@Override
	public double getPayout(IStockPath path) {
		List<PricePoint> price = path.getPrices();

		double avg = price.get(0).getPrice();

		for (int i = 1; i < price.size(); i++) {

			avg = i / (i + 1.0) * avg + price.get(i).getPrice() / (i + 1.0);

		}

		return Math.max(avg - strikePrice, 0);
	}

}
