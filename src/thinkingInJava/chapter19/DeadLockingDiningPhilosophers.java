package thinkingInJava.chapter19;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xuye on 2017年7月12日
 * <p>
 * 死锁demo，如果哲学家拿到了2个筷子，则可以进食。从结果来看，没有人能进食
 */
public class DeadLockingDiningPhilosophers {

	public static void main(String[] args) throws IOException {
		int ponder = 5;// 哲学家的思考时间
		int size = 5;// 筷子和哲学家的数量
		ExecutorService ex = Executors.newCachedThreadPool();
		// 初始化5双筷子
		Chopstick[] sticks = new Chopstick[size];
		for (int i = 0; i < size; i++) {
			sticks[i] = new Chopstick();
		}
		// 5个哲学家开始行动
		for (int j = 0; j < size; j++) {
			ex.execute(new Philosopher(sticks[j], sticks[(j + 1) % size], j,
					ponder));
		}
		System.out.println("press 'enter' to quit");
		System.in.read();
		ex.shutdownNow();
	}
}
