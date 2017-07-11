package thinkingInJava.chapter19;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuye on 2017年7月11日
 * <p>
 * 通过调用wait和notify挂起唤醒线程，实现线程间的协作
 */
public class WaxOMatic {
	public static void main(String[] args) throws Exception {
		Car car = new Car();
		ExecutorService ex = Executors.newCachedThreadPool();
		// 执行涂蜡和抛光任务
		ex.execute(new WaxOn(car));
		ex.execute(new WaxOff(car));
		// 等待一会后，停止全部任务
		TimeUnit.SECONDS.sleep(1);
		ex.shutdownNow();
	}
}

class Car {
	private boolean waxOn = false;

	/**
	 * 涂蜡中
	 */
	public synchronized void waxed() {
		waxOn = true;
		notifyAll();
	}

	/**
	 * 抛光中
	 */
	public synchronized void buffed() {
		waxOn = false;
		notifyAll();
	}

	/**
	 * 等待涂蜡，必须是同步的，因为wait需要在获得锁的情况下才能调用
	 */
	public synchronized void waitForWaxing() throws InterruptedException {
		while (!waxOn) {
			wait();
		}
	}

	/**
	 * 等待抛光，必须是同步的，因为wait需要在获得锁的情况下才能调用
	 */
	public synchronized void waitForBuffing() throws InterruptedException {
		while (waxOn) {
			// wait后，调用者所在的线程被挂起，synchronized获取的锁被释放
			wait();
		}
	}

}

/**
 * Created by xuye on 2017年7月11日
 * <p>
 * 涂蜡
 */
class WaxOn implements Runnable {
	private Car car;

	public WaxOn(Car car) {
		this.car = car;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				// 开始涂蜡
				car.waxed();
				System.out.println("wax on");
				TimeUnit.MILLISECONDS.sleep(200);
				// 涂完了，挂起当前任务，等待抛光
				car.waitForBuffing();
			}
		} catch (InterruptedException e) {
			System.out.println("exiting wax on via interrupted");
		}
		System.out.println("ending wax on task");
	}
}

/**
 * Created by xuye on 2017年7月11日
 * <p>
 * 涂完蜡，去抛光
 */
class WaxOff implements Runnable {
	private Car car;

	public WaxOff(Car car) {
		this.car = car;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				// 去抛光
				car.buffed();
				System.out.println("wax off");
				TimeUnit.MILLISECONDS.sleep(200);
				// 抛光完了，挂起当前任务，等待涂蜡
				car.waitForWaxing();
			}
		} catch (InterruptedException e) {
			System.out.println("exiting wax off via interrupted");
		}
		System.out.println("ending wax off task");
	}

}
