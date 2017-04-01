package thinkingInJava.chapter8;

public class SubTest {
	public static void main(String[] args) {
		//测试父、子类有同名变量时的调用
		SuperClass sup = new SubClass();
		//如果引用是父类，调用同名变量会使用父类的那个同名变量
		System.out.println("super.commonValue = "+sup.commonValue);
		SubClass sub = new SubClass();
		//如果引用是子类，调用同名变量会使用子类的那个同名变量
		System.out.println("sub.commonValue = "+sub.commonValue);
		//如果子类想使用父类的同名变量，就需要显式的调用父类的变量，比如在父类里加个调用方法，然后调用该方法
	}
}
