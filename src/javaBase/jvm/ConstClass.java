package javaBase.jvm;

/**
 * Created by xuye on 2017年11月13日
 * <p>
 * 该类里定义了常量，引用该类的常量，不会触发该类的初始化
 */
public class ConstClass {
	static {
		System.out.println("----ConstClass init----");
	}

	public static final String HELLOWORLD = "hello world";
}
