package optionPricing;

import java.util.List;

/**
 * 
 * @author Simeon
 * CallOptionPayOutImpl implements the IPayOut Interface
 * Call pay-out: max(S(T)-K)+
 * @see IPayOut
 */

public class CallOptionPayOutImpl implements IPayOut {
	private double strikePrice;

	public CallOptionPayOutImpl(double strikePrice) {

		this.strikePrice = strikePrice;

	}

	public CallOptionPayOutImpl(Option option) {

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
		return Math.max(price.get(price.size() - 1).getPrice() - strikePrice, 0);

	}

}
