package optionPricing;

import java.util.List;
/**
 * 
 * @author Simeon
 *  The interface IStockPath for creating StockPath. 
 *  The returned list should be ordered by date 
 */
public interface IStockPath {
public List<PricePoint> getPrices();

}
