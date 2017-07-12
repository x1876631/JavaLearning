package thinkingInJava.chapter19;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuye on 2017年7月11日
 * <p>
 * 模拟一个消费者生产者过程
 * <p>
 * 餐厅，服务员必须等待厨师准备好食物，厨师准备好以后，通知服务员，服务员上菜，然后返回继续等待
 */
public class Restaurant {
	Meal meal;
	ExecutorService ex = Executors.newCachedThreadPool();
	WaitPerson waitPerson = new WaitPerson(this);
	Chef chef = new Chef(this);

	public Restaurant() {
		ex.execute(chef);
		ex.execute(waitPerson);
	}

	public static void main(String[] args) {
		new Restaurant();
	}
}

class Meal {
	private final int orderNum;

	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}

	@Override
	public String toString() {
		return "Meal " + orderNum;
	}
}

/**
 * Created by xuye on 2017年7月11日
 * <p>
 * 服务员
 */
class WaitPerson implements Runnable {
	private Restaurant restaurant;

	public WaitPerson(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					// 如果此时还没有饭，则waitPerson所在线程挂起
					while (restaurant.meal == null) {
						wait();
					}
				}
				// 能走到这说明被唤醒了，饭做完了，拿到饭了。
				System.out.println("waitperson got " + restaurant.meal);

				/***
				 * 为什么要锁住restaurant.chef？锁住restaurant不是一样吗?
				 * 
				 * 改为锁restaurant，报异常了。 因为A对象执行notifyAll时，需要获取A对象的锁，
				 * 所以因为调用的restaurant.chef的notifyAll()，要锁住restaurant.chef。
				 * 
				 * 如果调用restaurant的notifyAll，那么就需要锁restaurant，效果是一样的。
				 * 这里之所以锁restaurant.chef是因为从业务逻辑上来讲，要唤醒厨师，而不是唤醒餐厅
				 */
				synchronized (restaurant.chef) {
					// 去送饭，并唤醒厨师去做下一顿
					restaurant.meal = null;
					restaurant.chef.notifyAll();
				}
			}
		} catch (InterruptedException e) {
			System.out.println("\nwaitPerson interrupted");
		}

	}
}

/**
 * Created by xuye on 2017年7月11日
 * <p>
 * 厨师
 */
class Chef implements Runnable {
	private Restaurant restaurant;// 餐厅
	private int count = 0;// 做了几次饭

	public Chef(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal != null) {
						// 挂起厨师线程
						wait();
					}
				}
				if (++count == 10) {
					// 做10次饭以后，打烊关门
					System.out.println("out of food,closing");
					restaurant.ex.shutdownNow();
				}
				// 做好了饭，唤醒服务员
				System.out.print("order up...   ");
				synchronized (restaurant.waitPerson) {
					restaurant.meal = new Meal(count);
					restaurant.waitPerson.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			System.out.println("\nchef interrupted");
		}

	}
}
