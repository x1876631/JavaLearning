package thinkingInJava.chapter19;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuye on 2017年7月9日
 * <p>
 * 模仿一个花园统计所有大门总人流量的过程
 */
public class OrnamentalGarden {
	public static void main(String[] args) throws Exception {
		ExecutorService ex = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			// 设置花园共有5个大门，所有大门一起统计流量，加起来就是总人流量
			ex.execute(new Entrance(i));
		}

		// 3秒后结束统计
		TimeUnit.SECONDS.sleep(3);
		Entrance.cancel();
		ex.shutdown();

		if (!ex.awaitTermination(250, TimeUnit.MILLISECONDS)) {
			// 这个方法会等待每个任务结束，如果所有任务在超时时间到达前全部结束，则返回true
			// 否则如果还有任务没结束，则没结束的任务会退出其run方法，结束任务
			System.out.println("some tasks were not terminated!");
		}
		System.out.println("Total: " + Entrance.getTotalCount());
		System.out.println("sum of entrances: " + Entrance.sumEntrances());
	}
}

class Count {
	private int count = 0;
	private Random rand = new Random(47);

	// 如果去掉了同步，则count值会明显少于所有大门的人流数总和
	public synchronized int increment() {
		int temp = count;
		// if (rand.nextBoolean()) {
		// Thread.yield();
		// }
		return (count = ++temp);
	}

	public synchronized int value() {
		return count;
	};

}

/**
 * Created by xuye on 2017年7月9日
 * <p>
 * 统计花园大门人流量的任务
 */
class Entrance implements Runnable {

	private static Count count = new Count();// 静态变量作为所有大门的总人流数
	private static List<Entrance> entrances = new ArrayList<Entrance>();// 大门列表
	private int number = 0;// 当期大门的人流量
	private final int id;// 当前大门
	private static volatile boolean canceled = false;// 是否取消统计

	public static void cancel() {
		System.out.println("----停止统计----");
		canceled = true;
	}

	public Entrance(int id) {
		this.id = id;
		entrances.add(this);
	}

	@Override
	public void run() {
		while (!canceled) {
			synchronized (this) {
				// 增加当前大门的人流量
				++number;
			}
			// 统计此时的总人流数，这个自增统计要同步
			System.out.println(this + " total: " + count.increment());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("sleep interrupted");
			}
		}
		System.out.println("stopping " + this);
	}

	public synchronized int getValue() {
		return number;
	}

	@Override
	public String toString() {
		return "Entrance " + id + ": " + getValue();
	}

	public static int getTotalCount() {
		return count.value();
	}

	/**
	 * 统计所有大门的总人流量
	 */
	public static int sumEntrances() {
		int sum = 0;
		for (Entrance en : entrances) {
			sum += en.getValue();
		}
		return sum;
	}

}