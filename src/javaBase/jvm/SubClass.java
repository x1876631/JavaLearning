package javaBase.jvm;

/**
 * Created by xuye on 2017年11月13日
 * <p>
 * java类初始化测试类2，子类
 */
public class SubClass extends SuperClass {
	static {
		System.out.println("----SubClass init----");
	}
}
