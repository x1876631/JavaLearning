package thinkingInJava.chapter19;

/**
 * Created by xuye on 2017-6-29
 * <p>
 * 学习定义一个任务，灯泡倒计时结束后关闭
 */
public class LiftOff implements Runnable {

	protected int countDown = 5;// 倒计时的数值
	private static int taskCount = 0;// 类实例数的记录值，每创建一个实例，taskCount都加1
	private final int id = taskCount++;// 当前任务的id

	public LiftOff() {
	}

	public LiftOff(int countDown) {
		this.countDown = countDown;
	}

	/**
	 * @return 倒计时情况
	 */
	public String Status() {
		return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff!")
				+ "), ";
	}

	@Override
	public void run() {
		while (countDown-- > 0) {
			// 每次倒计时-1，输出状态
			System.out.println(Status());
			// 主动建议系统切换线程
			Thread.yield();
		}
	}

}
