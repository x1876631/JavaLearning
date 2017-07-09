package thinkingInJava.chapter19;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xuye on 2017年7月9日
 * <p>
 * 比较同步代码块、同步方法的性能区别
 */
public class CriticalSection {
	static void testApproaches(PairManager pm1, PairManager pm2) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		PairManipulator pmp1 = new PairManipulator(pm1);
		PairManipulator pmp2 = new PairManipulator(pm2);
		PairChecker pc1 = new PairChecker(pm1);
		PairChecker pc2 = new PairChecker(pm2);
		executorService.execute(pmp1);
		executorService.execute(pmp2);
		executorService.execute(pc1);
		executorService.execute(pc2);

		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (Exception e) {
			System.out.println("sleep interrunped");
		}
		System.out.println("pmp1: " + pmp1 + "\npmp2: " + pmp2);
		System.exit(0);
	}

	public static void main(String[] args) {
		PairManager pm1 = new PairManager1();
		PairManager pm2 = new PairManager2();
		testApproaches(pm1, pm2);
	}
}

/**
 * 非线程安全的类
 * <p>
 * 因为约束条件需要2个变量维护相同的值 而这2个变量靠自增操作变化，自增不是线程安全的，可能在多线程情况下被破坏，导致2者的值不一致
 */
class Pair {
	private int x, y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Pair() {
		this(0, 0);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void incrementX() {
		x++;
	}

	public void incrementY() {
		y++;
	}

	public String toString() {
		return "x: " + x + ", y: " + y;
	}

	@SuppressWarnings("serial")
	public class PairValuesNotEqualException extends RuntimeException {
		public PairValuesNotEqualException() {
			super("Pair values not equal: " + Pair.this);
		}
	}

	public void checkState() {
		if (x != y) {
			throw new PairValuesNotEqualException();
		}
	}
}

abstract class PairManager {
	AtomicInteger checkCounter = new AtomicInteger(0);
	protected Pair p = new Pair();

	// 同步的list，对list的操作都是线程安全的
	private List<Pair> storage = Collections
			.synchronizedList(new ArrayList<Pair>());

	public synchronized Pair getPair() {
		return new Pair(p.getX(), p.getY());
	}

	protected void store(Pair p) {
		storage.add(p);
		// 同步再加上睡眠，性能差到1亿倍了...去掉后大概是6倍的差距
		// try {
		// TimeUnit.MILLISECONDS.sleep(50);
		// } catch (Exception e) {
		//
		// }
	}

	public abstract void increment();
}

/**
 * 同步全部方法
 */
class PairManager1 extends PairManager {

	@Override
	public synchronized void increment() {
		p.incrementX();
		p.incrementY();
		store(getPair());
	}
}

/**
 * 使用同步代代码块同步关键部分
 */
class PairManager2 extends PairManager {

	@Override
	public void increment() {
		Pair temp;
		synchronized (this) {
			// 仅仅在自增时锁住当前对象
			p.incrementX();
			p.incrementY();
			temp = getPair();
		}
		// 存储时不需要锁住资源，这样相比锁住整个方法，可以让线程有更多的访问当前对象的时间
		// 所以检查次数比锁住整个方法多得多
		store(temp);
	}
}

/**
 * pair的自增任务
 */
class PairManipulator implements Runnable {

	private PairManager pm;

	public PairManipulator(PairManager pm) {
		this.pm = pm;
	}

	@Override
	public void run() {
		while (true) {
			pm.increment();
		}
	}

	public String toString() {
		return "Pair: " + pm.getPair() + " checkCount = "
				+ pm.checkCounter.get();
	}

}

/**
 * pair的检查任务
 */
class PairChecker implements Runnable {
	private PairManager pm;

	public PairChecker(PairManager pm) {
		this.pm = pm;
	}

	@Override
	public void run() {
		while (true) {
			// 每检查一次时，都将检查标记增加1
			pm.checkCounter.incrementAndGet();
			// 锁住资源时，getPair会被阻塞住，之后的检查自然也需要等待了，所以锁住时间越长，检查次数越少
			pm.getPair().checkState();
		}
	}
}
