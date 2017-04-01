package thinkingInJava.chapter8;

/**
 * Created by xuye on 2017-03-31
 *
 * 子测试类
 * 
 * 运行后打印：
 * 
 * static SuperClass.super3 initialized---1
 * 
 * static SubClass.sub2 initialized---2
 * 
 * SubClass is running---3
 * 
 * SuperClass.super1 initialized---4
 * 
 * super1 = 9 , super2 = 0---5
 * 
 * SubClass.sub1 initialized---6
 * 
 * sub1 = 47---7
 * 
 * sub2 = 47---8
 * 
 * 1、2先打印是因为编译期就会执行static。然后是3,类在运行时执行了main函数，第一步打印该句
 * 
 * 如果main函数中不创建SubClass则只会执行上诉3句
 * 
 * 接下来，创建子类时先执行了父类的初始化，首先是类成员变量的初始化，所以4执行。 然后执行了父类的构造函数，即5。
 * 
 * 在父类初始化后，才会进行子类的初始化，同样是先初始化类成员变量，6执行。然后执行子类的构造函数，7、8执行。
 * 
 * 【总结】静态语句，编译时就执行。创建对象时，如果有父类先执行父类的初始化过程。初始化时先初始化类成员变量，最后执行构造函数。
 */
public class SubClass extends SuperClass {
	
	public int commonValue = 2;//与父类同名的变量，如果引用是父类，则调用该变量时用的是父类的同名变量
	
	private int sub1 = printInit("SubClass.sub1 initialized");

	public SubClass() {
		super();//有这个super()和没有一样，如果没有会默默地调用
		System.out.println("sub1 = " + sub1);
		System.out.println("sub2 = " + sub2);
	}

	private static int sub2 = printInit("static SubClass.sub2 initialized");

	public static void main(String[] args) {
		System.out.println("SubClass is running");
		SubClass subClass = new SubClass();
	}
}
