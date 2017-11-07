package designPatterns.strategy;

/**
 * created by xuye on 2017年11月7日
 * 
 * 地铁计费策略类
 */
public class SubwayStrategy implements CalculateStrategy {

	@Override
	public int calculatePrice(int km) {
		if (km <= 6) {
			return 3;
		} else if (km > 6 && km <= 12) {
			return 4;
		} else if (km > 12 && km <= 22) {
			return 5;
		} else if (km > 22 && km <= 32) {
			return 6;
		} else {
			return 7;
		}
	}

}
