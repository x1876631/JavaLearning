package javaBase.concurrent;

/**
 * Created by xuye on 2018年1月11日
 * <p>
 * ABC三个线程，C线程要等A和B线程的结果，AB并行，用什么数据结构或者模型解决？ <br/>
 * 参考：http://blog.csdn.net/xiao__gui/article/details/9213413
 */
public class JoinTest {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		Thread thread1 = new JoinThread();
		thread1.start();

		Thread thread2 = new JoinThread(5);
		thread2.start();

		try {
			// main线程等待子线程结束才去执行
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("JoinTest 线程执行总时长：" + (end - start));
	}
}
