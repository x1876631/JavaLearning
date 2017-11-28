package designPatterns.decorator;

/**
 * created by xuye on 2017年11月28日
 */
public class DecoratorTest {
	public static void main(String[] args) {
		Person person = new Boy();
		PersonCloth cloth = new ExpensiveCloth(person);
		cloth.dressed();
	}
}
