package javaBase.memory;

/**
 * Created by xuye on 2017年11月2日
 * <p>
 * 可能由于重排序导致结果与预期不符的例子，使用volatile修饰词解决
 * 
 * 正常我们希望先执行writer再执行reader后，i应该=1，但在多线程中最后的结果可能会是i=0。
 * 原因是对于没有依赖关系的2个变量的操作，可能还有重排序，
 * 比如对a和flag的操作，处理器A可能会先执行flag=true，然后处理器B执行if(flag)，
 * 此时a=0，所以i=0。或者先执行i=a*a=0，写入重排序缓冲，然后根据flag=true，得到最后i值=0。
 * 
 * 我们希望有依赖关系，不要重排序，可以使用volatile关键字修饰flag，这样规定了对flag的写一定会发生在对flag的读之前，
 * 即操作2一定发生在操作3前。
 * 
 * but等等，还有个问题，23有依赖关系，先4，再2和3，最后1还是错误的啊。
 * 有一个happens-before的规则保证了执行4的时候一定能看到1的结果： 1、程序顺序规则要求先1后2，先3后4 2、volatile要求先2后3
 * 3、传递性规则：先1后2，先2后3，先3后4，所以一定会先1后4(指可见性关系，非执行顺序)
 */
public class ReorderExample {

	int a = 0;
	/**
	 * 1、加了volatile只能保证写flag的结果一定在读flag之前可见，但不能保证reader一定在writer之前执行...
	 * 2、编译器对volatile的重排序规则表：
	 * 
	 * 2.1、当第2个操作是volatile写时，无论前1个操作是什么变量的什么操作，都不允许重排序
	 * 
	 * 2.2、当第1个操作是volatile读时，无论第2个操作是什么变量什么操作，都不允许重排序
	 * 
	 * 2.3、当第1个操作是volatile写，第2个操作是volatile读时，不允许重排序
	 * 
	 * 具体实现是编译器生成字节码时，插入了内存屏障
	 */
	volatile boolean flag = false;
	int i = 0;

	public void writer() {
		System.out.println("----writer----");
		a = 1;// 操作1
		System.out.println("a=1");
		flag = true;// 操作2
		System.out.println("flag=true");
	}

	public void reader() {
		System.out.println("----reader----");
		if (flag) {// 操作3
			System.out.println("if(flag=1)");
			i = a * a;// 操作4
			System.out.println("i=a*a");
		}

		// 最后查看i的值
		System.out.println("\n---i=" + i + "----");
	}

	public static void main(String[] args) {
		final ReorderExample re = new ReorderExample();
		Thread a = new Thread(new Runnable() {

			@Override
			public void run() {
				re.writer();
			}
		});
		Thread b = new Thread(new Runnable() {

			@Override
			public void run() {
				re.reader();
			}
		});
		a.start();
		b.start();

		// 注意，不要再这里读取，无论怎么同步，i都有可能是0，因为打印log会发现a和b此时可能还没执行
		// System.out.println("\n---i=" + re.i + "----");

	}

}
