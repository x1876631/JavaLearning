package thinkingInJava.chapter19;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xuye on 2017年7月7日
 * <p>
 * 使用concurrent类库的同步类，尝试获取锁
 */
public class AttemptLocking {
	private ReentrantLock lock = new ReentrantLock();

	/**
	 * 无等待地尝试获取锁
	 */
	public void untimed() {
		// 尝试获取锁，如果锁成功了返回true
		boolean captured = lock.tryLock();
		try {
			System.out.println("tryLock(): " + captured);
		} finally {
			if (captured) {
				System.out.println("tryLock(), unlock...");
				lock.unlock();
			}
		}
	}

	/**
	 * 有等待地尝试获取锁
	 */
	public void timed() {
		boolean captured = false;
		try {
			// 如果在给定的等待时间内没有被另一个线程占用并且当前线程尚未被锁定，则获取该锁
			captured = lock.tryLock(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		try {
			System.out
					.println("lock.tryLock(2, TimeUnit.SECONDS): " + captured);
		} finally {
			if (captured) {
				System.out
						.println("lock.tryLock(2, TimeUnit.SECONDS), unlock...");
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) {
		final AttemptLocking al = new AttemptLocking();
		al.untimed();
		al.timed();
		new Thread() {
			{
				setDaemon(true);
			}

			public void run() {
				al.lock.lock();
				System.out.println("acquired");
			};
		}.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		al.untimed();
		al.timed();
	}
}
