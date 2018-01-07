package designPatterns.producerAndConsumer;

import java.util.concurrent.BlockingQueue;

/**
 * Created by xuye on 2018年1月7日
 * <p>
 * 消费者，从缓冲队列里拿产品
 */
public class Consumer<T> {
	private BlockingQueue<T> mQueue;

	public Consumer(BlockingQueue<T> queue) {
		this.mQueue = queue;
	}

	public T take() throws InterruptedException {
		return mQueue.take();
	}
}
