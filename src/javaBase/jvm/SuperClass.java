package javaBase.jvm;

/**
 * Created by xuye on 2017年11月13日
 * <p>
 * java类初始化测试类1，父类
 */
public class SuperClass {
	static {
		System.out.println("----SuperClass init----");
	}
	public static int value = 123;
}
