package optionPricing;

/**
 * 
 * @author Simeon
 * The decorator patter-Antithetic The Antithetic approach used
 * to achieve faster convergence of the Monte Carlo simulation Suppose
 * you use n1,----nk to generate a path. The next path is generated by -n1,....-nk.
 * 
 */
public class AntitheticRandomVectorGeneratorImpl implements IRandomVectorGenerator {

	private IRandomVectorGenerator vectorGenerator;
	private Boolean flag = false;// The state of the AntitheticRandomVectorGenerator process
	private double[] originalVector;// Carry the last generated random vector

	public AntitheticRandomVectorGeneratorImpl(IRandomVectorGenerator vectorGenerator) {

		this.vectorGenerator = vectorGenerator;

	}

	@Override

	// Generating a random vector and negate it.

	/**
	 * @return a random vector
	 */
	public double[] getVector() {

		if (flag) {

			flag = false;

			for (int i = 0; i < originalVector.length; i++) {

				originalVector[i] *= -1;

			}

		} else {
			flag = true;
			originalVector = vectorGenerator.getVector();
		}

		return this.originalVector;
	}

}
