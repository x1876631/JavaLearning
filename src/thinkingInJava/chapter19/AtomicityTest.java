package thinkingInJava.chapter19;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xuye on 2017年7月7日
 * <p>
 * java非原子操作测试类
 * 
 */
public class AtomicityTest implements Runnable {
	private int i = 0;

	public synchronized int getValue() {
		return i;
	}

	private synchronized void evenIncrement() {
		i++;
		i++;
	}

	@Override
	public void run() {
		while (true) {
			evenIncrement();
		}
	}

	public static void main(String[] args) {
		ExecutorService ex = Executors.newCachedThreadPool();
		AtomicityTest at = new AtomicityTest();
		ex.execute(at);

		// AtomicityTest子线程一直执行自增操作，main线程不断读取AtomicityTest的value
		// getValue不是线程安全的，因为getValue不是同步读取的，其可能在i是奇数时(即evenIncrement执行过程中)被读取
		// 解决办法就是给getValue()加锁，这样getvalue就必须在evenIncrement()释放锁后才执行
		while (true) {
			int value = at.getValue();
			if (value % 2 != 0) {
				System.out.println(value);
				System.exit(0);
			}
		}
	}
}
