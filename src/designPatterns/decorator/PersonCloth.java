package designPatterns.decorator;

/**
 * created by xuye on 2017年11月28日
 * 
 * 装饰模式中的装饰者，这里以一个人所穿的衣服为例
 * 
 * 好像代理啊，只不过代理是代理者和被代理者都实现同一个接口。这里是继承同样的抽象类<br/>
 * 又有点像模板模式，写个抽象类，然后子类去扩展。不够模板方法重点在于规定流程
 */
public class PersonCloth extends Person {

	protected Person mPerson;

	public PersonCloth(Person person) {
		this.mPerson = person;
	}

	@Override
	public void dressed() {
		// 这里可以对被装饰的对象的方法，做扩展，而不用去修改被装饰的对象
		// 扩展逻辑1...
		mPerson.dressed();
		// 扩展逻辑2...
	}

}
