package java.lang;

/**
 * Created by xuye on 2017年11月14日
 * <p>
 * 自定义了一个java.lang.String类
 * 
 * 虽然定义了以后，引用了String类的类，比如{@link TestString}引用的String变成了这个类，
 * 但是实际使用时加载的还是rt.jar包里的String.class
 * 
 * 这就是双亲委托的作用! 保证了使用的安全，防止其他人自定义基础类，埋下恶意代码
 * 
 * 实际上，就算打破双亲委托，自定义实现一个classloader，去加载我们自定义实现的String类，也是无效的。
 * 因为jvm的实现，保证java.*开头的类必须被BootStrapClassLoader去加载，
 * 在java.lang.ClassLoader的源码里，创建Class对象需要先做一个检查，其实有个步骤是如果名字以java.开头，直接创建失败抛异常
 */
//public class String {
//
//	public String(String string) {
//		System.out.println("----new my String----");
//	}
//
// }
