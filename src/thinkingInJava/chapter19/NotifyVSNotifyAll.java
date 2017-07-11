package thinkingInJava.chapter19;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuye on 2017年7月11日
 * <p>
 * 比较notify和notifyAll
 */
public class NotifyVSNotifyAll {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService ex = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			ex.execute(new Task());
		}
		ex.execute(new Task2());

		TimeUnit.SECONDS.sleep(1);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			boolean prod = true;

			@Override
			public void run() {
				if (prod) {
					System.out.println("\nnotify()");
					// 唤醒等待Task.blocker锁的线程
					Task.blocker.prod();
					prod = false;
				} else {
					System.out.println("\nnotifyAll()");
					// notifyAll只唤醒了所有等待Task.blocker锁的任务
					Task.blocker.prodAll();
					prod = true;
				}
			}
		}, 400, 400);
		TimeUnit.SECONDS.sleep(5);

		timer.cancel();
		System.out.println("\nTimer canceled");
		TimeUnit.MILLISECONDS.sleep(500);

		System.out.println("Task2.blocker.prodAll()");
		// Task1的唤醒不影响task2
		Task2.blocker.prodAll();
		TimeUnit.MILLISECONDS.sleep(500);

		System.out.println("\nshut down now");
		ex.shutdownNow();
	}
}

class Blocker {
	synchronized void waitingCall() {
		try {
			while (!Thread.interrupted()) {
				System.out.println(Thread.currentThread() + " is to wait....");
				// 挂起当前任务所在线程
				wait();
				System.out.println(Thread.currentThread() + "is waked....");
			}
		} catch (InterruptedException e) {
			System.out.println("Blocker waked by interrupt! thread: "
					+ Thread.currentThread());
		}
	}

	synchronized void prod() {
		// 唤醒单一任务
		System.out.println(Thread.currentThread() + "is notify");
		notify();
	}

	synchronized void prodAll() {
		// 唤醒所有任务
		System.out.println(Thread.currentThread() + "is notifyAll");
		notifyAll();
	}
}

class Task implements Runnable {
	static Blocker blocker = new Blocker();

	@Override
	public void run() {
		blocker.waitingCall();
	}
}

class Task2 implements Runnable {
	static Blocker blocker = new Blocker();

	@Override
	public void run() {
		blocker.waitingCall();
	}
}