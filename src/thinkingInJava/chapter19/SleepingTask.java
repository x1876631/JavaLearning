package thinkingInJava.chapter19;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SleepingTask extends LiftOff {
	@Override
	public void run() {
		try {
			while (countDown-- > 0) {
				System.out.println(Status());
				// 让任务中止一段时间，这里指定为100ms
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Interrunpted");
		}
	}

	public static void main(String[] args) {
		ExecutorService exService = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exService.execute(new SleepingTask());
		}
		exService.shutdown();
	}
}
