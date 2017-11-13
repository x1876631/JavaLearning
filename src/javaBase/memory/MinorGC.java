package javaBase.memory;

/**
 * Created by xuye on 2017年11月13日
 * <p>
 * 测试minor gc,执行代码：
 * 
 * 进入当前源代码所在目录，先编译一下Minor类，执行：java
 * Minor.java得到一个Minor.class，然后再执行这个class，需要加一些jvm参数，命令如下：
 * 
 * java -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8 javaBase/memory/MinorGC
 * 
 * 指定初始堆大小20MB，最大堆大小20MB，新生代10MB，eden区占新生代的80%
 * 
 */
public class MinorGC {
	private static final int _1MB = 1024 * 1024;

	public static void testAllocation() {
		byte[] a1, a2, a3, a4;
		a1 = new byte[3 * _1MB];
		a2 = new byte[3 * _1MB];
		a3 = new byte[1 * _1MB];
		// a4 = new byte[4 * _1MB];
	}

	public static void main(String[] args) {
		MinorGC.testAllocation();
		/*
		 * 执行gc结果：
		 * 
		 * [PSYoungGen:7504K->704K(9216K)]表示新生代总可用9216k，回收前占用7504k，回收后占用704k，
		 * 7504K->6856K(19456K)表示总堆内存占用从7504k变为了6856k，总堆内存为19456k。
		 * 之所以新生代少了，是因为有6MB多的内存从新生代转移到了老年代里，所以新生代减少，而这些对象还在使用无法被回收，所以总堆大小几乎不变
		 * 从多次修改代码打印log后发现，在添加了3+3
		 * =6MB内存后(不知道为啥6MB占用7.4M)，再放入1MB内存时，eden区貌似没有位置了(7.4+1>8)，所以发生了一次minor
		 * gc。一部分内存((7504-704)-(7504-6856) = 约6MB)从新生代转移到了老年代，给后面要放的4MB腾地方。
		 * 
		 * [Full GC (Ergonomics) [PSYoungGen: 704K->0K(9216K)] [ParOldGen:
		 * 6152K->6629K(10240K)] 6856K->6629K(19456K)表示发生了full
		 * gc，新生代从704K变成了0k，老年代从6152k变成了6629k，总堆从6856K变为了6629k
		 * 
		 * 但是后面为啥又发生了full gc呢？
		 * 估计是因为担保失败(新生代使用复制算法gc，将eden和survivor的还存活的对象复制到另一块survivor上
		 * ，另一块survivor不够时
		 * ，通过老年代进行分配担保，让存活对象直接进入老年代，如果老年代最大可用连续空间>新生代所有对象之和，则可以分配担保)
		 * 
		 * 
		 * gc日志：
		 * 
		 * [GC (Allocation Failure) [PSYoungGen: 7504K->704K(9216K)]
		 * 7504K->6856K(19456K), 0.0043317 secs] [Times: user=0.02 sys=0.01,
		 * real=0.00 secs]
		 * 
		 * [Full GC (Ergonomics) [PSYoungGen: 704K->0K(9216K)] [ParOldGen:
		 * 6152K->6629K(10240K)] 6856K->6629K(19456K), [Metaspace:
		 * 2775K->2775K(1056768K)], 0.0050477 secs] [Times: user=0.02 sys=0.00,
		 * real=0.01 secs]
		 * 
		 * 
		 * 顺带一提：这里的gc收集器，新生代使用的是parallel scavenge收集器，老年代使用的是parallel old收集器
		 */

		/*
		 * 执行代码后，堆内存的情况：
		 * 
		 * 新生代总可用9216k，使用了5360k。其中eden区总可用8192k，使用了65%，from
		 * survivor总可用1024k，使用了0%
		 * 
		 * 老年代总可用10240k，使用了6629k，占比64%
		 * 
		 * Heap PSYoungGen total 9216K, used 5360K [0x00000007bf600000,
		 * 0x00000007c0000000, 0x00000007c0000000) eden space 8192K, 65% used
		 * [0x00000007bf600000,0x00000007bfb3c308,0x00000007bfe00000) from space
		 * 1024K, 0% used
		 * [0x00000007bfe00000,0x00000007bfe00000,0x00000007bff00000) to space
		 * 1024K, 0% used
		 * [0x00000007bff00000,0x00000007bff00000,0x00000007c0000000) ParOldGen
		 * total 10240K, used 6629K [0x00000007bec00000, 0x00000007bf600000,
		 * 0x00000007bf600000) object space 10240K, 64% used
		 * [0x00000007bec00000,0x00000007bf279450,0x00000007bf600000) Metaspace
		 * used 2782K, capacity 4486K, committed 4864K, reserved 1056768K class
		 * space used 301K, capacity 386K, committed 512K, reserved 1048576K
		 */
	}
}
