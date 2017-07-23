package thinkingInJava.chapter19;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuye on 2017年7月12日
 * <p>
 * 哲学家
 */
public class Philosopher implements Runnable {
	private Chopstick left;
	private Chopstick right;
	private final int id;
	private final int ponderFactor;// 思考因子
	private Random rand = new Random(47);

	private void pause() throws InterruptedException {
		if (ponderFactor == 0) {
			return;
		}
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
	}

	public Philosopher(Chopstick left, Chopstick right, int id, int ponder) {
		this.left = left;
		this.right = right;
		this.id = id;
		this.ponderFactor = ponder;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				// 思考
				System.out.println(this + " " + "is thinking");
				pause();

				// 尝试拿起右筷子
				System.out.println(this + " " + "try to take right");
				right.take();

				// 尝试拿起左筷子
				System.out.println(this + " " + "try to take left");
				left.take();

				// 吃饭
				System.out.println(this + " " + "is eating");
				pause();

				// 吃完放下左右筷子
				right.drop();
				left.drop();
			}
		} catch (InterruptedException e) {
			System.out.println(this + " " + "exiting via interrupt");
		}
	}

	@Override
	public String toString() {
		return "philosopher " + id;
	}
}
