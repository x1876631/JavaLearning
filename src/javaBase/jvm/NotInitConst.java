package javaBase.jvm;

/**
 * Created by xuye on 2017年11月13日
 * <p>
 * 引用类里的常量，不会触发该类的初始化
 */
public class NotInitConst {
	public static void main(String[] args) {
		System.out.println(ConstClass.HELLOWORLD);
		// 没有执行----ConstClass init----，说明没有初始化ConstClass
		// 实际在编译后，ConstClass的常量已经被编译进当前类的常量池了，所以其实没有对ConstClass.HELLOWORLD有引用
	}
}
