package javaBase.jvm;

/**
 * Created by xuye on 2017年11月13日
 * <p>
 * 通过数组定义引用类，该类不会初始化
 */
public class NotInitArray {
	public static void main(String[] args) {
		SuperClass[] superClasses = new SuperClass[10];
		// 没有任何输出，说明类SuperClass并没有被初始化。
		// ps：顺带一提，数组创建指令newarray创建了一个由jvm自动生成的数组类
	}
}
