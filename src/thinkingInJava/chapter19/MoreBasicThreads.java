package thinkingInJava.chapter19;

/**
 * Created by xuye on 2017-6-29
 * <p>
 * 多线程调用
 */
public class MoreBasicThreads {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread(new LiftOff()).start();
		}
		System.out.println("waiting for LiftOff");
	}
}
