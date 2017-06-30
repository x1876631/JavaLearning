package thinkingInJava.chapter19;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xuye on 2017年6月29日
 * <p>
 * 
 */
public class SingleThreadExecutor {
	public static void main(String[] args) {
		ExecutorService exService = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			// SingleThreadExecutor每次只执行一个任务，其他任务会在前一个任务结束后顺序执行
			exService.execute(new LiftOff());
		}
		exService.shutdown();
	}
}
