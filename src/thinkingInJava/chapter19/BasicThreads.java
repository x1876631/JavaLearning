package thinkingInJava.chapter19;

/**
 * Created by xuye on 2017-6-29
 * <p>
 * 使用线程执行任务
 */
public class BasicThreads {
	public static void main(String[] args) {
		Thread thread = new Thread(new LiftOff());
		// start为该子线程执行必需的初始化，然后调用子线程自己的run方法，run里调用传入线程的任务的run方法
		thread.start();
		System.out.println("Waiting for LiftOff");
	}
}
