package designPatterns.factory;

/**
 * created by xuye on 2017年11月23日
 * 
 * 抽象工厂的具体实现，生产哪个product由该类具体指定，将获取产品的人和产品解耦了
 */
public class FactoryA extends Factory {

	@Override
	public Product createProduct() {
		System.out.println("----工厂生产了指定产品----");
		return new ProductA();
	}

	@Override
	public <T extends Product> T createProduct(Class<T> clz) {
		System.out.println("----工厂生产了指定产品----");
		T t = null;
		try {
			t = (T) Class.forName(clz.getName()).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

}
