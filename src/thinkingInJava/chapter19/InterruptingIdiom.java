package thinkingInJava.chapter19;

import java.util.concurrent.TimeUnit;

/**
 * Created by xuye on 2017年7月10日
 * <p>
 * 检查中断，对于所有需要清理的对象，创建后都需要紧跟try-finally，使得无论run何时退出都可以清理
 */
public class InterruptingIdiom {
	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new Blocked3());
		t.start();
		// 这里1000和1100是2种结果
		TimeUnit.MILLISECONDS.sleep(1100);
		t.interrupt();
	}
}

class NeedsCleanup {
	private final int id;

	public NeedsCleanup(int id) {
		this.id = id;
		System.out.println("needsCleanup " + id);
	}

	public void cleanup() {
		System.out.println("cleaning up " + id);
	}
}

class Blocked3 implements Runnable {
	private volatile double d = 0.0;

	@Override
	public void run() {
		try {
			// 检查中断
			while (!Thread.interrupted()) {
				// point1，如果中断发送在1和2之间，(sleep前或sleep中)，则任务会在第一次试图调用阻塞操作前，通过异常退出
				/***
				 * 输出 needsCleanup 1 ，sleeping ，cleaning up 1 ， exiting
				 * viaInterruptedException
				 */
				NeedsCleanup n1 = new NeedsCleanup(1);
				try {
					System.out.println("sleeping");
					TimeUnit.SECONDS.sleep(1);
					// point2，如果在这之后调用了中断，循环会结束，所有本地对象被销毁，最后循环会经由while的顶部退出
					/***
					 * 输出 needsCleanup 1 ，sleeping ，needsCleanup
					 * 2，calcuating，finished time-consuming operation，cleaning
					 * up 2， cleaning up 1 ，needsCleanup 1 ，sleeping ，cleaning
					 * up 1， exiting via InterruptedException
					 */
					NeedsCleanup n2 = new NeedsCleanup(2);
					try {
						System.out.println("calcuating");
						for (int i = 1; i < 2500000; i++) {
							d = d + (Math.PI + Math.E) / d;
						}
						System.out.println("finished time-consuming operation");
					} finally {
						n2.cleanup();
					}
				} finally {
					n1.cleanup();
				}
			}
			System.out.println("exiting via while() test");
		} catch (InterruptedException e) {
			System.out.println("exiting via InterruptedException");
		}
	}
}