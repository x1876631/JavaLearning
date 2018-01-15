package designPatterns.singleton;

public class SingleTonTest {
	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				PerfectSingleton.getInstance();
			}
		};

		// 多线程去调用单例
		Thread thread1 = new Thread(runnable);
		Thread thread2 = new Thread(runnable);
		thread1.start();
		thread2.start();
	}
}
