package thinkingInJava.chapter19;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xuye on 2017年7月7日
 * <p>
 * 使用java原子类保证原子性
 */
public class AtomicityIntegerTest implements Runnable {
	private AtomicInteger i = new AtomicInteger(0);

	public synchronized int getValue() {
		return i.get();
	}

	private synchronized void evenIncrement() {
		i.addAndGet(2);
	}

	@Override
	public void run() {
		while (true) {
			evenIncrement();
		}
	}

	public static void main(String[] args) {
		// 5s后自动退出程序
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				System.out.println("aborting");
				System.exit(0);
			}
		}, 5000);

		ExecutorService ex = Executors.newCachedThreadPool();
		AtomicityIntegerTest at = new AtomicityIntegerTest();
		ex.execute(at);

		// 这里使用java内置的基础原子类，保证了线程安全
		while (true) {
			int value = at.getValue();
			if (value % 2 != 0) {
				System.out.println(value);
				System.exit(0);
			}
		}
	}
}
