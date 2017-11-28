package designPatterns.decorator;

/**
 * created by xuye on 2017年11月28日
 *
 * 装饰模式中的被装饰对象，这里以一个男孩为例
 */
public class Boy extends Person {

	@Override
	public void dressed() {
		System.out.println("----男孩穿衣服啦----");
		System.out.println("基础：穿了内裤");
	}

}
