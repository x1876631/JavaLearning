package thinkingInJava.chapter19;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xuye on 2017年7月6日
 * <p>
 * 消费者任务
 */
public class EvenChecker implements Runnable {

	private IntGenerator ig;
	private final int id;

	public EvenChecker(IntGenerator ig, int id) {
		this.ig = ig;
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("eventChecker id: " + id);
		// 消费者任务循环检查生成器的值，如果产生的不是偶数就停止生产
		while (!ig.isCanceled()) {
			int value = ig.next();
			if (value % 2 != 0) {
				System.out.println(value + " not even! stop...");
				ig.cancel();
			}
		}
	}

	public static void test(IntGenerator intGenerator, int count) {
		System.out.println("press ctrl-c to exit...");
		// 循环产生多个消费者任务
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++) {
			executorService.execute(new EvenChecker(intGenerator, i));
		}
		executorService.shutdown();
	}

	public static void test(IntGenerator ig) {
		test(ig, 10);
	}

}
