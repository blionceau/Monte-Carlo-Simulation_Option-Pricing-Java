package optionPricing;

import org.apache.commons.math3.random.*;

/**
 * 
 * @author Simeon
 * RandomVectorGeneratorImpl class implements the IRandomVectorGenerator interface
 */
public class RandomVectorGeneratorImpl implements IRandomVectorGenerator {

	private UncorrelatedRandomVectorGenerator vectorGenerator;
	private int dimension;
	private double[] mean;
	private double[] variance;

	/**
	 * 
	 * @param dimension
	 */
	public RandomVectorGeneratorImpl(int dimension) {

		this.dimension = dimension;
		RandomGenerator vectorRg = new JDKRandomGenerator();
		vectorRg.setSeed((int) System.currentTimeMillis());// test long data type
		GaussianRandomGenerator myGenerator = new GaussianRandomGenerator(vectorRg);

		this.vectorGenerator = new UncorrelatedRandomVectorGenerator(this.dimension, myGenerator);
	}

	/**
	 * Constructor takes the following parameters:
	 * @param mean
	 * @param variance
	 */
	public RandomVectorGeneratorImpl(double[] mean, double[] variance) {

		this.mean = mean;
		this.variance = variance;
		RandomGenerator vectorRg = new JDKRandomGenerator();
		vectorRg.setSeed((int) System.currentTimeMillis());// test long data type
		GaussianRandomGenerator myGenerator = new GaussianRandomGenerator(vectorRg);
		this.vectorGenerator = new UncorrelatedRandomVectorGenerator(this.mean, this.variance, myGenerator);

	}

	/**
	 * @return a vector
	 */
	@Override
	public double[] getVector() {

		return this.vectorGenerator.nextVector();
	}

}
