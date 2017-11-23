package designPatterns.factory;

/**
 * created by xuye on 2017年11月23日
 * 
 * 抽象工厂，具体生产什么由子类实现
 */
public abstract class Factory {
	public abstract Product createProduct();

	/**
	 * 使用反射去生产产品
	 * 
	 * @param clz
	 *            产品类对象
	 * @return 产品实例
	 */
	public abstract <T extends Product> T createProduct(Class<T> clz);
}
