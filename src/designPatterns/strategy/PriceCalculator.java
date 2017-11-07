package designPatterns.strategy;

/**
 * created by xuye on 2017年11月7日
 * 
 * 当一个问题有多个解决方案时，使用if else挨个判断选择方案导致代码臃肿，耦合性高，越来越难以维护的问题
 * 
 * 策略模式，能解决上述问题，具体见{@link TranficCalculator}
 * 
 * 以交通工具的费用计算来举例：
 * 
 * 当要添加一种交通方式时，就需要在PriceCalculator类里加一个方法，并且也得修改calculatePrice()方法
 */
public class PriceCalculator {

	private static final int BUS = 1;
	private static final int SUBWAY = 2;

	/**
	 * 公交
	 */
	private int busPrice(int km) {
		int extraTotal = km - 10;// 超过的km数
		int extraFactor = extraTotal / 5;// 超过多少个5km
		int fraction = extraTotal % 5;
		int price = 1 + extraFactor * 1;
		return fraction > 0 ? ++price : price;
	}

	/**
	 * 地铁
	 */
	private int subwayPrice(int km) {
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

	private int calculatePrice(int km, int type) {
		if (type == BUS) {
			return busPrice(km);
		} else if (type == SUBWAY) {
			return subwayPrice(km);
		} else {
			return 0;
		}
	}

	public static void main(String[] args) {
		PriceCalculator calculator = new PriceCalculator();
		System.out.println("坐16km公交车的票价：" + calculator.calculatePrice(16, BUS));
		System.out.println("坐16km地铁的票价：" + calculator.calculatePrice(16, SUBWAY));

	}
}
