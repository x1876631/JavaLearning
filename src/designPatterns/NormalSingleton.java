package designPatterns;

/**
 * created by xuye on 2017年11月5日
 * 
 * 单例模式，最常见的设计模式，当在整个系统中只需要拥有一个这样的对象时，使用该模式，比如imageLoader
 * 
 * 本例为懒汉式单例，需要使用时才去实例化
 */
public class NormalSingleton {

	// 静态的实例对象，通过静态方法getInstance返回给调用者
	private static NormalSingleton sInstance;

	// 私有的构造方法，只能内部实例化，外部调用者只能通过开发的getInstance方法获取该类实例
	private NormalSingleton() {
	}

	// 静态的获取实例的方法
	public static NormalSingleton getInstance() {
		// ----写法1----
		// if (sInstance == null) {
		// sInstance = new NormalSingleton();
		// }

		// 写法1在多线程模式下会线程安全的问题
		// 当线程A发现sInstance==null时，去初始化，初始化时线程B也进入执行，发现sInstance还是==null，也去初始化，这样就创建了多个实例
		// 【解决办法】对getInstance加锁，但是只有第一次需要同步，之后其实都不需要同步，方法加锁很浪费资源

		// 更好的同步方法是，仅对sInstance = new NormalSingleton()加锁，如下：
		// ----写法2----
		// if (sInstance == null) {
		// synchronized (NormalSingleton.class) {
		// sInstance = new NormalSingleton();
		// }
		// }
		// 但是这种加锁写法还是有问题，线程A进入同步块获取锁去初始化，线程B进入同步块后阻塞，
		// 等线程A初始化后释放锁后，线程B获取了锁，但是没有检查实例是否初始化，会又创建一个实例。
		// 所以同步块里还得对实例做一次判断，改为如下写法：
		// ----写法3----
		if (sInstance == null) {
			synchronized (NormalSingleton.class) {
				if (sInstance == null) {
					sInstance = new NormalSingleton();
				}
			}
		}
		// 这就是著名的双重检查锁定的单例，但是由于指令重排序的情况下，可能在线程A未实例化完成时，sInstance就不为null了，
		// 这样线程B可能会获得一个未实例化完整的实例，使用sInstance里面的变量时可能会有问题
		// 【解决办法】将sInstance变量用volatile修饰，阻止了重排序，sInstance！=null一定在new
		// NormalSingleton()完成以后

		// 但是！不同虚拟机的实现不一样，可能对volatile不够支持，所以这种写法还是有问题，更好的写法可以采用静态内部类的形式

		return sInstance;
	}

}
