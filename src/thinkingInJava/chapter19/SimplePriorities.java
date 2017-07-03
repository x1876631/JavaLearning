package thinkingInJava.chapter19;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xuye on 2017年7月3日
 * <p>
 * 线程优先级
 */
public class SimplePriorities implements Runnable {
	private int countDown = 5;
	private volatile double d;
	private int priority;

	public SimplePriorities(int priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		// 记录当前任务所在线程名和倒计时
		return Thread.currentThread() + ": " + countDown;
	}

	@Override
	public void run() {
		// 设置当前线程的优先级
		Thread.currentThread().setPriority(priority);
		while (true) {
			for (int i = 0; i < 100000; i++) {
				// 这里增加一个比较耗时的计算，以便线程调度可以介入
				d += (Math.PI + Math.E) / i;
				if (i % 1000 == 0) {
					Thread.yield();
				}
			}
			System.out.println(this);
			if (--countDown == 0) {
				return;
			}
		}
	}

	public static void main(String[] args) {
		// 虽然java有10个优先级，但是各操作系统并不确定有几个，所以比较好的使用方法就是使用min/max/normal3个优先级
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			// 这5个线程使用最低优先级
			executorService.execute(new SimplePriorities(Thread.MIN_PRIORITY));
		}
		// 这个线程使用最高优先级
		executorService.execute(new SimplePriorities(Thread.MAX_PRIORITY));
		executorService.shutdown();
	}

}
