package designPatterns.factory;

/**
 * created by xuye on 2017年11月23日<br/>
 * 
 * 简单的工厂模式测试类<br/>
 * 工厂模式的关键就在于其提供具体的产品给用户，用户不同关心去生产哪个产品，只要去使用工厂生产产品就好了<br/>
 * 实现用户与具体产品解耦，用户要做的事就是：使用工厂，生产产品，调用产品。这个过程无论怎么换产品，都不用动该过程代码，只要改工厂就好了<br/>
 * 
 * 【工厂与策略的区别】<br/>
 * 具体实现代码很相似，都是调用不同实例的父类统一接口，实现解耦，但是含义、作用不一样<br/>
 * 举个例子，去披萨店吃披萨，用工厂和策略看这件事<br/>
 * 
 * 工厂模式去吃：<br/>
 * 点一个披萨吃，不关心披萨怎么做的。你无法对披萨做处理，只能获取披萨。侧重对象的创建(资源分发)，比如数据库选择，类库加载，而不关心怎么选择<br/>
 * 
 * 策略模式去吃：<br/>
 * 点一个披萨，但是得自己做，原料a、b、c，制作工序1、2、3，怎么生产披萨你说了算，最后也获取了一个披萨。侧重对策略(披萨工序)的替换和扩张 <br/>
 * 
 * 这么看策略也是工厂，传入参数给工厂，工厂根据参数生产产品。只不过策略更灵活，可以传入参数。而工厂一般不允许传入参数，就是获取规定好的产品
 */
public class FactoryTest {
	public static void main(String[] args) {
		Factory factory = new FactoryA();

		// 生产哪个产品、产品如何使用，已经与某个具体的产品解耦了，都由生产该产品的工厂决定
		Product product1 = factory.createProduct();
		product1.method();

		// 使用反射动态获取产品，这种传入参数控制产品就有点像策略，每个策略类就是一个产品
		// 具体可以参考策略模式类：{@link designPatterns.strategy.TranficCalculator}
		Product product2 = factory.createProduct(ProductB.class);
		product2.method();
	}
}
