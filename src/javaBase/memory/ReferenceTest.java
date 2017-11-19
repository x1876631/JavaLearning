package javaBase.memory;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Created by xuye on 2017年11月17日
 * <p>
 * java的4种引用：强、软、弱、虚
 */
public class ReferenceTest {

	public static void main(String[] args) {
		ReferenceTest test = new ReferenceTest();
		// test.testSoftReference();
		// test.testWeakReference();
		test.testPhantomReference();
	}

	public void testStrongReference() {
		String string = new String("testStrongReference");
		System.gc();
		System.out.println("gc后的强引用值：" + string);
	}

	/**
	 * 测试软引用
	 */
	public void testSoftReference() {
		// 创建一个对象，并用强引用string持有对象
		String string = new String("testSoftReference");
		// 再用一个软引用softReference持有着强引用持有的对象
		SoftReference<String> softReference = new SoftReference<String>(string);
		// 将强引用指向空，现在刚才创建的对象只有一个弱引用持有着了
		string = null;
		System.gc();// gc的源码里说明，调用后jvm会尽最大努力去回收无用对象
		// 建议内存回收后，再看软引用持有的对象还存在吗，正常来说应该在的，毕竟软引用只有内存不足时才会回收
		System.out.println("gc后的软引用值：" + softReference.get());
	}

	/**
	 * 测试弱引用
	 */
	public void testWeakReference() {
		String string = new String("testWeakReference");
		WeakReference<String> weakReference = new WeakReference<String>(string);
		System.out.println("gc前的弱引用值：" + weakReference.get());
		string = null;// 如果不执行这个置空操作，由于强引用还在，是不会回收string持有的对象的
		System.gc();
		// gc后，输出为null，说明弱引用被回收
		System.out.println("gc后的弱引用值：" + weakReference.get());
	}

	/**
	 * 测试虚引用
	 */
	public void testPhantomReference() {
		String string = new String("testPhantomReference");
		ReferenceQueue<String> queue = new ReferenceQueue<String>();
		// 创建虚引用时，必须和引用队列一起使用
		PhantomReference<String> phantomReference = new PhantomReference<String>(
				string, queue);
		System.out.println("gc前的虚引用值：" + phantomReference.get());
		System.out.println("gc前虚引用队列值：" + queue.poll());
		string = null;
		System.gc();
		System.out.println("gc后的虚引用值：" + phantomReference.get());
		// 里面有个引用地址，说明虚引用对象被回收后，将虚引用添加到了队列里
		System.out.println("gc后虚引用队列值：" + queue.poll());
	}

}
