package thinkingInJava.chapter19;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuye on 2017年7月10日
 * <p>
 * 演示各种中断，其中Sleep阻塞可以中断，IO和sync阻塞不可中断
 */
public class Interrupting {
	private static ExecutorService ex = Executors.newCachedThreadPool();

	static void test(Runnable r) throws InterruptedException {
		Future<?> f = ex.submit(r);
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("interrupt " + r.getClass().getSimpleName());
		f.cancel(true);
		System.out.println("interrupt send to " + r.getClass().getSimpleName());
	}

	public static void main(String[] args) throws Exception {
		test(new SleepBlocking());
		test(new IOBlocked(System.in));
		test(new SynchronizedBlocked());
		TimeUnit.MILLISECONDS.sleep(3);
		System.out.println("aborting with system.exit(0)");
		System.exit(0);
	}
}

/**
 * Created by xuye on 2017年7月10日
 * <p>
 * 睡眠阻塞中断
 */
class SleepBlocking implements Runnable {

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException");
		}
		System.out.println("exiting sleepBlocked.run()");
	}

}

/**
 * Created by xuye on 2017年7月10日
 * <p>
 * IO阻塞中断
 */
class IOBlocked implements Runnable {

	private InputStream in;

	public IOBlocked(InputStream in) {
		this.in = in;
	}

	@Override
	public void run() {
		try {
			System.out.println("waiting for read()");
			in.read();
		} catch (IOException e) {
			if (Thread.currentThread().isInterrupted()) {
				System.out.println("interrupted from blocked i/o");
			} else {
				throw new RuntimeException(e);
			}
		}
		System.out.println("exiting IOBlocked.run()");
	}
}

class SynchronizedBlocked implements Runnable {

	public SynchronizedBlocked() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				f();
			}
		}).start();
	}

	/**
	 * 永远都不释放锁
	 */
	public synchronized void f() {
		while (true) {
			Thread.yield();
		}
	}

	@Override
	public void run() {
		System.out.println("trying to call f()");
		f();
		System.out.println("exiting SynchronizedBlocked.run()");
	}

}
