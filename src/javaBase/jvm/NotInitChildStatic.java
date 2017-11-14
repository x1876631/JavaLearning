package javaBase.jvm;

/**
 * Created by xuye on 2017年11月13日
 * <p>
 * 子类调用父类的静态字段，不会触发子类的初始化，只会触发父类的初始化
 */
public class NotInitChildStatic {
	public static void main(String[] args) {
		System.out.println(SubClass.value);
		// 运行结果是输出了----SuperClass init----和123，而没有输出----SubClass init----
	}
}
