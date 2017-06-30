package thinkingInJava.chapter19;

public class MainThread {
	public static void main(String[] args) {
		LiftOff launch = new LiftOff();
		launch.run();// 任务的run不会产生线程，这个任务是被main线程调用的
	}
}
