package thinkingInJava.chapter19;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuye on 2017年7月9日
 * <p>
 * 使用ThreadLocal根除对变量的共享，以解决线程安全的问题
 */
public class ThreadLocalVariableHolder {

	/**
	 * 唯一的静态ThreadLocal对象，存储一个共享数据，里面维护各线程自己所需的数据
	 */
	private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
		private Random r = new Random(47);

		protected synchronized Integer initialValue() {
			return r.nextInt(10000);
		}
	};

	public static void increment() {
		value.set(value.get() + 1);
	}

	public static int get() {
		return value.get();
	}

	public static void main(String[] args) throws Exception {
		ExecutorService ex = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			ex.execute(new Accessor(i));
		}
		TimeUnit.SECONDS.sleep(3);
		ex.shutdown();
	}
}

class Accessor implements Runnable {
	private final int id;

	public Accessor(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			ThreadLocalVariableHolder.increment();
			System.out.println(this);
			Thread.yield();
		}
	}

	@Override
	public String toString() {
		return "#" + id + ": " + ThreadLocalVariableHolder.get();
	}
}
