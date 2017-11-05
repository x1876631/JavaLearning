package javaBase.memory;

/**
 * Created by xuye on 2017年11月5日
 * <p>
 * java内存模型，浅解： http://wiki.jikexueyuan.com/project/java-concurrent/java-memory-
 * model.html
 * 
 * 1、原始类型的本地(局部)变量，存在栈上。
 * 
 * 2、引用类型的本地(局部)变量，引用存在栈上，对象本身存在堆上。
 * 
 * 3、无论什么类型的成员变量，都存在堆上。
 * 
 * 
 */
public class MyRunnable implements Runnable {
	@Override
	public void run() {
		methorOne();
	}

	/**
	 * 当线程执行了methorOne方法后，会在线程栈上创建local1和local2的私有拷贝。
	 * 
	 * 变量localVar1彼此独立，变量localVar2的不同拷贝都指向对上的同一个对象。
	 */
	public void methorOne() {
		int localVar1 = 45;// 原始类型的本地变量，保存在栈上
		MyShareObject localVar2 = MyShareObject.sharedInstance;// 引用类型的本地变量
		methodTwo();
	}

	public void methodTwo() {
		// 引用对象的引用，存在线程栈里，实例化的对象存在堆里。
		Integer localVar1 = new Integer(99);
	}
}
