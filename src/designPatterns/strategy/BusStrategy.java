package designPatterns.strategy;

/**
 * created by xuye on 2017年11月7日
 * 
 * 公交车计费策略类
 */
public class BusStrategy implements CalculateStrategy {

	@Override
	public int calculatePrice(int km) {
		int extraTotal = km - 10;// 超过的km数
		int extraFactor = extraTotal / 5;// 超过多少个5km
		int fraction = extraTotal % 5;
		int price = 1 + extraFactor * 1;
		return fraction > 0 ? ++price : price;
	}

}
