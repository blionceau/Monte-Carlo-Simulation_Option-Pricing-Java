package optionPricing;

import java.util.ArrayList;

/**
 * 
 * @author Simeon
 *StatsCollector that helps to decide when to stop the MC simulation.
 * Keeps track of stats like average and standard deviation.
 * @See StatsCollector()
 */
public class StatsCollector {

	private double[] c = { 2.515517, 0.802853, 0.010328 };
	private double[] d = { 1.432788, 0.189269, 0.001308 };
	private double _xp;
	private double p;
	private double mean ;
	private double standardDev;
	private double meanSquare;
	private ArrayList<Double> myArray;
/**
 * 
 * @param p
 */
	public StatsCollector(double p) {
		this.p = p;
	}
/**
 * 
 * @param t 
 * @return _xp value
 */
	public double RationalApproximation(double t) {
		_xp = t - ((c[2] * t + c[1])*t + c[0]) / (((d[2] *t + d[1]) * t + d[0]) * t + 1.0);
		return _xp;
	}

	public double get_xp() {
		return _xp;
	}

	public void set_xp(double _xp) {
		this._xp = _xp;
	}

	
	/**
	 * 
	 * @return t value
	 */
	public double NomalCDFInverse() {

		if (p < 0.5) {
			// F^-1(p) = - G^-1(p)

			return RationalApproximation(Math.sqrt(-2.0 * Math.log(p)));

		}
		// F^-1(p) = G^-1(1-p)
		else
			return RationalApproximation(Math.sqrt(-2.0 * Math.log(1 - p)));

	}

	public StatsCollector() {

		this.myArray = new ArrayList<Double>();

	}

	public void add(double x) {
		
		
		int sampleSize = myArray.size() + 1;
		
		myArray.add(new Double(x));
		mean = (sampleSize - 1.0) / sampleSize * mean + x / sampleSize;
		meanSquare = (sampleSize - 1.0) / sampleSize * meanSquare + x * x / sampleSize;
		this.standardDev = Math.sqrt(meanSquare - mean * mean);

	}

	public double getMean() {
		return mean;
	}

	public double getStandardDev() {
		return this.standardDev;
	}



}
