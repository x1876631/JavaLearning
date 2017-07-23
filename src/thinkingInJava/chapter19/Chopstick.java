package thinkingInJava.chapter19;


/**
 * Created by xuye on 2017年7月12日
 * <p>
 * 筷子
 */
public class Chopstick {
	private boolean taken = false;// 当前筷子是否被占用

	/**
	 * 拿起筷子
	 */
	public synchronized void take() throws InterruptedException {
		while (taken) {
			wait();
		}
		taken = true;
	}

	/**
	 * 放下筷子
	 */
	public synchronized void drop() {
		taken = false;
		notifyAll();
	}
}
