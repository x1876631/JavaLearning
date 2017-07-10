package thinkingInJava.chapter19;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xuye on 2017年7月10日
 * <p>
 * 通过interrupt打断被互斥所阻塞的调用
 */
public class Interrupting2 {
	public static void main(String[] args) throws Exception {
		Thread thread = new Thread(new Blocked2());
		thread.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("issuing t.interrupt()");
		thread.interrupt();
	}
}

class BlockedMutex {
	private Lock lock = new ReentrantLock();

	public BlockedMutex() {
		lock.lock();
	}

	public void f() {
		try {
			// 除非当前线程中断，否则获取不到锁
			lock.lockInterruptibly();
			System.out.println("lock acquired in f()");
		} catch (InterruptedException e) {
			// 中断后就可以继续运行了
			System.out.println("interrupt from lock acquosition in f()");
		}
	}
}

class Blocked2 implements Runnable {
	// 创建blockMutex时，该对象已上锁
	BlockedMutex blockedMutex = new BlockedMutex();

	@Override
	public void run() {
		System.out.println("waiting for f() in BlockedMutex");
		// 调用时由于获取不到锁而阻塞
		blockedMutex.f();
		System.out.println("broken out of blocked call");
	}
}
