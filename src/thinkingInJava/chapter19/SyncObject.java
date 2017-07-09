package thinkingInJava.chapter19;

public class SyncObject {
	public static void main(String[] args) {
		final DualSynch ds = new DualSynch();
		new Thread() {
			public void run() {
				ds.f();
			};
		}.start();
		ds.g();
	}
}

/**
 * Created by xuye on 2017年7月9日
 * <p>
 * 锁的对象不同，则同步相互独立，一个方法不会因为另一个方法的同步而阻塞，输出会是g()、f()交替输出
 * <p>
 * 如果是锁的同一个对象，则会先输出所有的g()再输出所有的f()
 */
class DualSynch {
	private Object syncObject = new Object();

	/**
	 * 锁住类对象
	 */
	public synchronized void f() {
		for (int i = 0; i < 5; i++) {
			System.out.println("f()");
			Thread.yield();
		}
	}

	/**
	 * 锁住类里的对象
	 */
	public void g() {
		synchronized (syncObject) {
			for (int i = 0; i < 5; i++) {
				System.out.println("g()");
				Thread.yield();
			}
		}
	}
}
