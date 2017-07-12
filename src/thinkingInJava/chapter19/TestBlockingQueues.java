package thinkingInJava.chapter19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by xuye on 2017年7月12日
 * <p>
 * 使用blockingQueue保证任务的同步
 */
public class TestBlockingQueues {
	static void getKey() {
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	static void getKey(String message) {
		System.out.println(message);
		getKey();
	}

	static void test(String msg, BlockingQueue<LiftOff> queue) {
		System.out.println(msg);
		LiftOffRunner runner = new LiftOffRunner(queue);
		Thread t = new Thread(runner);
		t.start();
		// 添加任务到同步队列里，去执行
		for (int i = 0; i < 5; i++) {
			runner.add(new LiftOff(5));
		}
		// 等待控制台输入回车
		getKey("press 'Enter' (" + msg + ")");
		// 控制台读取到数据后，打断当前线程，结束任务
		t.interrupt();
		System.out.println("Finished " + msg + " test");
	}

	public static void main(String[] args) {
		test("LinkedBlockingQueue", new LinkedBlockingDeque<LiftOff>());
		test("ArrayBlockingQueue", new ArrayBlockingQueue<LiftOff>(3));
		test("SynchronousQueue", new SynchronousQueue<LiftOff>());
	}
}

class LiftOffRunner implements Runnable {

	private BlockingQueue<LiftOff> rockers;

	public LiftOffRunner(BlockingQueue<LiftOff> queue) {
		this.rockers = queue;
	}

	public void add(LiftOff lo) {
		try {
			rockers.put(lo);
		} catch (InterruptedException e) {
			System.out.println("interrupt during put()");
		}
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				LiftOff rocket = rockers.take();
				rocket.run();
			}
		} catch (InterruptedException e) {
			System.out.println("waking from take()");
		}
		System.out.println("Exiting LiftOffRunner");
	}

}
