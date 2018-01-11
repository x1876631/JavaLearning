package javaBase.concurrent;

public class JoinThread extends Thread {

	private int mSleepTime = 2;

	public JoinThread() {

	}

	public JoinThread(int sleeptime) {
		this.mSleepTime = sleeptime;
	}

	public void run() {
		System.out.println(this.getName() + "：JoinThread线程开始");
		try {
			// 子线程休眠
			System.out.println("----JoinThread线程休眠" + mSleepTime + "s----\n");
			Thread.sleep(mSleepTime * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.getName() + "：JoinThread线程结束");
	}
}
