package designPatterns.producerAndConsumer;

import java.util.concurrent.BlockingQueue;

/**
 * Created by xuye on 2018年1月7日
 * <p>
 * 
 * 生成者，向缓冲队列里生成产品
 */
public class Producer<T> {
	private BlockingQueue<T> mQueue;

	public Producer(BlockingQueue<T> queue) {
		this.mQueue = queue;
	}

	public boolean put(T product) {
		return mQueue.offer(product);
	}

}
