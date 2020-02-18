package optionPricing_Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.junit.Before;
import org.junit.Test;

import optionPricing.AsianCallOptionPayOutImpl;
import optionPricing.IPayOut;
import optionPricing.IStockPath;
import optionPricing.Option;
import optionPricing.PricePoint;
import optionPricing.StockPathImpl;

import static org.mockito.Mockito.*;

/**
 * 
 * @author Simeon ,Here I am testing the PayOuts and path generation
 */
public class MonteCarloSimulationTest {

	private static final double tolerance = 1e-12;
	private static int optionDays = 100;
	private static String optionName = "IBM";
	private static double initialPrice = 200;
	private static double interestRate = 0.0001;
	private static double volatility = 0.01;
	private static double strikePrice = 0;
	private static DateTime initialTime = new DateTime(2017, 10, 26, 10, 00);
	private static Period period = Period.days(optionDays);
	private static String payOutType = "AsianCall";
	private static Option option = new Option(optionName, payOutType, initialPrice, initialTime, period, interestRate,
			volatility, strikePrice);
	private static ArrayList<PricePoint> path0;

	/** Here I am testing the path of $1 decreasing per day */

	private static ArrayList<PricePoint> path1;

	/** Here I am testing the path of constant price */

	private static ArrayList<PricePoint> path2;

	/**
	 * Here I am testing the path of $1 increasing per day in the first half cycle
	 * and $1 decreasing per day in the last half cycle
	 */

	private static ArrayList<PricePoint> path3;

	/** Here I am testing the path of one initial price only */

	private static ArrayList<PricePoint> path4;

	/** Here I am testing five paths */

	@Before
	public void generateTestPath() {

		path0 = new ArrayList<PricePoint>();
		path1 = new ArrayList<PricePoint>();
		path2 = new ArrayList<PricePoint>();
		path3 = new ArrayList<PricePoint>();
		path4 = new ArrayList<PricePoint>();

		path0.add(new PricePoint(option.getInitialTime(), option.getInitialPrice()));

		path1.add(new PricePoint(option.getInitialTime(), option.getInitialPrice()));

		path2.add(new PricePoint(option.getInitialTime(), option.getInitialPrice()));

		path3.add(new PricePoint(option.getInitialTime(), option.getInitialPrice()));

		path4.add(new PricePoint(option.getInitialTime(), option.getInitialPrice()));

		double[] etaValue = new double[option.getPeriod().getDays()];

		double st0 = option.getInitialPrice();

		double st1 = option.getInitialPrice();

		double st2 = option.getInitialPrice();

		double st3 = option.getInitialPrice();

		DateTime t = option.getInitialTime();

		for (int i = 0; i < etaValue.length; i++) {

			t = t.plusDays(1);

			st0 = st0 + 1;

			st1 = st1 - 1;

			if (i < etaValue.length / 2)

				st3 = st3 + 1;

			else

				st3 = st3 - 1;

			path0.add(new PricePoint(t, st0));

			path1.add(new PricePoint(t, st1));

			path2.add(new PricePoint(t, st2));

			path3.add(new PricePoint(t, st3));

		}

	}

	@Test
	public void testAsianCallPayOut() {

		IStockPath paths = mock(StockPathImpl.class);

		when(paths.getPrices()).thenReturn(path0).thenReturn(path1).thenReturn(path2).thenReturn(path3)
				.thenReturn(path4);
		;

		IPayOut payOut = new AsianCallOptionPayOutImpl(strikePrice);

		assertEquals("wrong estimated PayOut", 250, payOut.getPayout(paths), tolerance);

		assertEquals("wrong estimated PayOut", 150, payOut.getPayout(paths), tolerance);

		assertEquals("wrong estimated PayOut", 200, payOut.getPayout(paths), tolerance);

		int n = option.getPeriod().getDays() / 2;

		assertEquals("wrong estimated PayOut", (n * (n + 1) - 50 + 101 * 200) / 101.0, payOut.getPayout(paths),
				tolerance);

		assertEquals("wrong estimated PayOut", 200, payOut.getPayout(paths), tolerance);
	}

}
