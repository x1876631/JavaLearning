package designPatterns.strategy;

/**
 * created by xuye on 2017年11月7日
 * 
 * 公交出行价格计算器，calculatePrice()方法没有if
 * else，只要在setStrategy()方法里传入不同的CalculateStrategy就可以切换到对应的策略
 * 
 * 策略模式将各种解决方案分离开，保证了策略选择类的不变性，进而解耦。
 * 
 * 另外由于各自维护自己的策略类，代码简单，所以每个策略的维护也更容易
 * 
 * 【实际应用】现在公司的qa对话业务中的消息展示，其实就是不同策略的选择，比如语音消息选择展示语音的UI，返回了语音类型的view。
 * 现在是在adapter做if else判断消息类型，选择对应的展示逻辑，30多个消息，1000多行代码都在adapter里
 * 
 * 【使用策略模式改进】
 * 
 * 1、创建一个抽象父类G7ViewHolder，有个统一的展示方法setData()，
 * 
 * 2、不同消息展示逻辑封装到各自的展示策略类里，比如语音消息展示策略封装到audioHolder里，然后继承该父类，实现统一展示方法。
 * 
 * 3、在adapter里只要负责调用父类的展示方法就好了。当然，仍然需要判断传入实例的类型。
 * 
 * 总结：将具体消息展示策略与【策略选择类adapter】解耦， adapter代码只要100多行，每种消息的展示策略代码也好维护了很多！
 */
public class TranficCalculator {
	private CalculateStrategy mCalculateStrategy;

	/**
	 * 将原本calculatePrice()方法里的策略判断交给了传入的参数
	 * 
	 * @param calculateStrategy
	 */
	public void setStrategy(CalculateStrategy calculateStrategy) {
		this.mCalculateStrategy = calculateStrategy;
	}

	/**
	 * 原本交通方式的判断和计算都写在calculatePrice里，每次修改交通方式都要改calculatePrice()方法，现在不用改了
	 */
	public int calculatePrice(int km) {
		// 无论什么策略类，都是CalculateStrategy类型，所以这段计算价格代码，无论是什么类型都不用改，进而实现解耦
		return mCalculateStrategy.calculatePrice(km);
	}

	public static void main(String[] args) {
		TranficCalculator calculator = new TranficCalculator();
		// 原本main里计算价格也要判断type并传入type，现在通过传入制定策略类，相当于选type了，没有增加更多的步骤，但是价格计算类里解耦了
		// 仔细想想，解耦其实是【***通过调用不同实例的父类接口的统一方法***】，实例上转型为一个类型，调用策略的执行代码都一样，就与具体策略无关了，就解耦了
		calculator.setStrategy(new BusStrategy());
		System.out.println("坐16km公交车的票价：" + calculator.calculatePrice(16));
	}
}
