package designPatterns.decorator;

/**
 * created by xuye on 2017年11月28日
 * 
 * 具体的装饰者，以一个昂贵的衣服为例
 * 
 * 装饰模式，其实就是将被装饰者的原有方法和新逻辑整合到一个新方法里
 */
public class ExpensiveCloth extends PersonCloth {
	public ExpensiveCloth(Person person) {
		super(person);
	}

	@Override
	public void dressed() {
		super.dressed();
		dressShirt();
		dressJean();
		dressLeather();
	}

	public void dressShirt() {
		System.out.println("扩展：穿了短袖");
	}

	public void dressJean() {
		System.out.println("扩展：穿了牛仔裤");
	}

	public void dressLeather() {
		System.out.println("扩展：穿了外衣");
	}

}
