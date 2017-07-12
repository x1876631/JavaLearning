package thinkingInJava.chapter19;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuye on 2017年7月12日
 * <p>
 * 使用同步队列，实现有序任务
 */
public class ToastOMatic {
	public static void main(String[] args) throws InterruptedException {
		ToastQueue dryQueue = new ToastQueue(), butteredQueue = new ToastQueue(), finishedQueue = new ToastQueue();
		ExecutorService ex = Executors.newCachedThreadPool();
		ex.execute(new Toaster(dryQueue));
		ex.execute(new Butterer(dryQueue, butteredQueue));
		ex.execute(new Jammer(butteredQueue, finishedQueue));
		ex.execute(new Eater(finishedQueue));
		TimeUnit.SECONDS.sleep(3);
		ex.shutdownNow();
	}
}

class Toast {
	/**
	 * 吐司烘干、涂黄油、涂果酱
	 */
	public enum Status {
		DRY, BUTTERED, JAMMED
	}

	private Status status = Status.DRY;
	private final int id;

	public Toast(int id) {
		this.id = id;
	}

	public void butter() {
		status = Status.BUTTERED;
	}

	public void jam() {
		status = Status.JAMMED;
	}

	public Status getStatus() {
		return status;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return "Toast " + id + ": " + status;
	}
}

class ToastQueue extends LinkedBlockingDeque<Toast> {

}

/**
 * Created by xuye on 2017年7月12日
 * <p>
 * 制作吐司
 */
class Toaster implements Runnable {
	private ToastQueue toastQueue;
	private int count = 0;
	private Random random = new Random(47);

	public Toaster(ToastQueue tq) {
		this.toastQueue = tq;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(500));
				Toast t = new Toast(count++);
				System.out.println(t);
				// 在制作吐司队列里添加一个制作吐司的任务
				toastQueue.push(t);
			}
		} catch (InterruptedException e) {
			System.out.println("Toaster interrupted");
		}
		System.out.println("Toaster off");
	}
}

class Butterer implements Runnable {
	private ToastQueue dryQueue, butteredQueue;

	public Butterer(ToastQueue dry, ToastQueue buttered) {
		dryQueue = dry;
		butteredQueue = buttered;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				// 从吐司制作的队列里拿出一个做好的吐司
				Toast t = dryQueue.take();
				t.butter();// 涂黄油
				System.out.println(t);
				// 添加到涂抹完的黄油队列里
				butteredQueue.push(t);
			}
		} catch (InterruptedException e) {
			System.out.println("buttered interrupted");
		}
		System.out.println("buttered off");
	}
}

class Jammer implements Runnable {
	private ToastQueue butterQueue, finishedQueue;

	public Jammer(ToastQueue buttered, ToastQueue finished) {
		this.butterQueue = buttered;
		this.finishedQueue = finished;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = butterQueue.take();
				t.jam();
				System.out.println(t);
				finishedQueue.put(t);
			}
		} catch (InterruptedException e) {
			System.out.println("jammer interrupt");
		}
		System.out.println("jammer off");
	}
}

class Eater implements Runnable {
	private ToastQueue finishedQueue;
	private int counter = 0;

	public Eater(ToastQueue finished) {
		this.finishedQueue = finished;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = finishedQueue.take();
				if (t.getId() != counter++
						|| t.getStatus() != Toast.Status.JAMMED) {
					System.out.println(">>> error: " + t);
					System.exit(1);
				} else {
					System.out.println("chomp! " + t + "\n");
				}
			}
		} catch (InterruptedException e) {
			System.out.println("eater interrupted");
		}
		System.out.println("eater off");
	}
}