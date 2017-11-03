package javaBase.memory;

/**
 * Created by xuye on 2017年11月3日
 * <p>
 * 学习java内存模型里final修饰词的作用 对于final字段的重排序规则：
 * 规则1、构造函数的final写操作，和把构造对象的引用赋值给一个引用变量，这2个操作不能重排序， 即构造函数里的final写操作一定在构造函数里被执行了
 * 前提是构造函数里没有引用逸出
 * 
 * 规则2、初次读取包含final字段的对象引用，和初次读取该引用对象的final字段，这2个操作不能重排序。即读取final字段前，
 * 一定会先读取含有这个final字段的对象引用
 * 
 * 规则3、构造函数里fianl修饰的成员里的成员的写入，与随后把这个对象引用赋值给一个引用对象，这2个操作不能重排序
 */
public class FinalExample {

	int i;
	// final字段只能被赋值1次，有3个地方可以赋值：1、声明变量时 2、非静态成员变量在构造函数里
	// 3、非静态成员变量在{}块里，静态成员变量在static{}块里
	final int j;
	static FinalExample obj;
	final int[] intArray;

	public FinalExample() {
		i = 1;// 操作1
		j = 2;// 操作2
		// obj = this;// 操作3，这样会导致引用逸出，进而破坏规则1，因为线程A执行这个构造函数时，操作3可能先被执行

		intArray = new int[1];// 操作11
		intArray[0] = 1;// 操作12
	}

	public static void writer() {
		// 这个方法做了2个操作，1个是构建了一个FinalExample对象，2是将对象引用赋值给引用变量obj
		// 当线程A构造对象时，可能由于重排序，导致操作1被重排出构造函数，而操作2一定会在构造中被执行，这就是final的作用
		obj = new FinalExample();// 操作4，操作12和操作4也不能重排序
	}

	public static void writerTwo() {
		obj.intArray[0] = 2;// 操作23
	}

	public static void reader() {
		if (obj != null) {// 操作5
			// 操作3重排序后，可能会先赋值，
			// 导致构造函数未完成时，对象的引用就可用了，进而线程B执行操作4时看可以进入obj!=null的代码块里

			FinalExample object = obj;
			int a = object.i;
			// 对j的读取，一定发生在包含j字段的obj对象引用赋值之后
			int b = object.j;

			System.out.println("a=" + a + " , b=" + b);
		}
	}

	public static void main(String[] args) {
		Thread a = new Thread(new Runnable() {

			@Override
			public void run() {
				FinalExample.writer();
			}
		});
		Thread b = new Thread(new Runnable() {

			@Override
			public void run() {
				FinalExample.reader();
			}
		});
		a.start();
		b.start();
	}

}
