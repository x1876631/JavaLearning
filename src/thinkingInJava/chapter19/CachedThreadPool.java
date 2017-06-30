package thinkingInJava.chapter19;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xuye on 2017-6-29
 * <p>
 * 使用线程池
 */
public class CachedThreadPool {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			// 使用线程池管理线程，而不是每次都新建线程
			executorService.execute(new LiftOff());
		}
		// shutdown后该executer不再接受新的任务
		executorService.shutdown();
	}
}
