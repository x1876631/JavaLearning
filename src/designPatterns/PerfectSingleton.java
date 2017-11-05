package designPatterns;

/**
 * created by xuye on 2017年11月5日
 * 
 * 使用静态内部类实现的，完美的单例模式，由jvm机制保证了线程安全、懒加载
 */
public class PerfectSingleton {

	/**
	 * 静态内部类不算是真正的内部类，只是借用外部类这层外壳，表示只希望由外部类使用，不会持有对外部类的引用
	 */
	private static class SingletonHolder {
		// jvm在访问一个类的静态变量时，才会把它加载到内存中，所以是懒加载单例
		private static PerfectSingleton sInstance = new PerfectSingleton();
	}

	private PerfectSingleton() {
	}

	public static PerfectSingleton getInstance() {
		// 1、当第一次执行getInstance时，才会去初始化SingletonHolder，而初始化过程中会初始化其静态域，进而创建了外部类的实例
		// 由于是静态域，所以只有装载类时才会执行一次，以后将不会再执行
		// 2、多线程访问不会有问题吗？不会，因为jvm机制保证了classloader进行类的加载只有一个线程在进行
		return SingletonHolder.sInstance;
	}
}
