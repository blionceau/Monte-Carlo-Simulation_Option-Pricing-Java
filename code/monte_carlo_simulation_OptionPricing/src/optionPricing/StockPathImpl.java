package optionPricing;

import java.util.ArrayList;
import java.util.List;


import org.joda.time.DateTime;
/**
 * 
 * @author Simeon
 * StockPathImpl class implements IStockPath Interface
 */
public class StockPathImpl implements IStockPath {

		private IRandomVectorGenerator vectorGenerator;
		private Option option;
		private ArrayList<PricePoint> path;

	
		public StockPathImpl (Option option,IRandomVectorGenerator vectGenerator ) {

			IRandomVectorGenerator standNormGenerator = new RandomVectorGeneratorImpl(option.getPeriod().getDays());
			IRandomVectorGenerator vectorGenerator = new AntitheticRandomVectorGeneratorImpl(standNormGenerator);

			this.vectorGenerator = vectorGenerator;

			this.option = option;

			this.generateNewPath();// or generateNewPath()

		}


		public void generateNewPath() { 

			this.path = new ArrayList<PricePoint>();

			this.path.add(new PricePoint(option.getInitialTime(),option.getInitialPrice()));

			double[] etaVector = vectorGenerator.getVector();

			double St = option.getInitialPrice();

			DateTime mytime = option.getInitialTime();

			for(int i=0; i<etaVector.length;i++) {

				mytime = mytime.plusDays(1);

				double volatility = option.getVolatility();

				St = St * Math.exp((option.getInterestRate() - volatility*volatility/2)+volatility*etaVector[i]);

				this.path.add(new PricePoint(mytime,St));

			}

		}
	/**
	 * @return path
	 */
	@Override
	public List<PricePoint> getPrices() {
		this.generateNewPath();
		return path;
	}

}
