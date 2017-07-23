package thinkingInJava.chapter19;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xuye on 2017年7月23日
 * <p>
 * 死锁需要同时满足4个条件： 1、资源不能被共享 2、任务持有资源并等待另外的资源 3、资源不能被任务抢夺 4、任务间有循环等待
 * <p>
 * 所以解除死锁的方法就是破坏上述4条条件之一，这里破坏了条件4
 */
public class FixedDiningPhilosophers {
	public static void main(String[] args) throws IOException {
		int ponder = 5;
		int size = 5;
		ExecutorService ex = Executors.newCachedThreadPool();
		Chopstick[] sticks = new Chopstick[size];
		for (int i = 0; i < size; i++) {
			sticks[i] = new Chopstick();
		}
		/***
		 * 原本有循环等待导致死锁(即每个哲学家都依次拿起右筷子再拿左，导致每个哲学家都在等待左筷子)
		 * 
		 * 现在破坏该循环等待，让最后的哲学家先拿左再拿右，这样第5个筷子会被4个哲学家拿走而导致筷子4和5都被释放，之后所有筷子都被释放
		 */
		for (int i = 0; i < size; i++) {
			if (i < (size - 1)) {
				ex.execute(new Philosopher(sticks[i], sticks[i + 1], i, ponder));
			} else {
				ex.execute(new Philosopher(sticks[0], sticks[i], i, ponder));
			}
		}
		System.out.println("press 'Enter' to quit");
		System.in.read();
		ex.shutdownNow();
	}
}
