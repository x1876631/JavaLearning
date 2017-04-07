package thinkingInJava.chapter14;

import java.util.Random;

import utils.PrintUtils;

public class ClassInitialization {

	//实例变量不执行new是不会初始化的
	private int int1 = PrintUtils.printInit("ClassInitialization.int1 is init",
			1);
	
	//静态变量和static块是在类加载的初始化阶段赋值和执行的
	static {
		System.out.println("Initializing ClassInitialization");
	}
	
	public static Random random = new Random(47);

	public static void main(String[] args) throws ClassNotFoundException {
		//执行某个类的main函数前，会先去加载该类，自然静态变量和static块就会在main函数之前执行过了
		System.out.println("\nmain is called\n");

		// 使用.class获取Class类对象时，不会去初始化该类(初始化是类加载过程中的最后一步，用于初始化类变量和static块)，而是等到调用该类静态对象时才去初始化
		Class initable = Initable.class;
		System.out.println("After creating Initable ref");
		//调用了Initable.staticFinal这句后，Initable会初始化，给静态变量赋值并执行static块，输出Initializing Initable
		//调用编译常量时，可以不初始化该类就可以调用，比如Initable.staticFinal
		System.out.println(Initable.staticFinal);
		System.out.println(Initable.staticFinal2);

		//正常情况：Initable2先执行static{},再打印Initable2.staticNonFinal
		//为什么Initable中staticFinal2引用ClassInitialization的静态字段和使用常量，执行static {}的顺序是不一样的？
		System.out.println(Initable2.staticNonFinal);
		
		//Class.forName会立刻执行该类的初始化，这点与使用.class获取Class对象不同
		Class initable3 = Class.forName("thinkingInJava.chapter14.Initable3");
		System.out.println("After creating Initable3 ref");
		System.out.println(Initable3.staticNonFinal);
	}

}

class Initable {
	private int int1 = PrintUtils.printInit("Initable.int1 is init", 1);
	// 注意1和2都是static final的，编译期间就会被放入常量池
	static final int staticFinal = 47;
	// 调用ClassInitialization的静态字段应该会触发ClassInitialization的初始化吧？
	//ps：这个地方引用ClassInitialization的静态字段和使用常量，执行static {}的顺序是不一样的!
//	static final int staticFinal2 = 100;
	static final int staticFinal2 = ClassInitialization.random.nextInt(1000);
	static {
		System.out.println("Initializing Initable");
	}
}

class Initable2 {
	private int int1 = PrintUtils.printInit("Initable2.int1 is init", 1);
	static int staticNonFinal = 147;
	static {
		System.out.println("Initializing Initable2");
	}
}

class Initable3 {
	private int int1 = PrintUtils.printInit("Initable3.int1 is init", 1);
	static int staticNonFinal = 74;
	static {
		System.out.println("Initializing Initable3");
	}
}