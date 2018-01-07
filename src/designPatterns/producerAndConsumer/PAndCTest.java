package designPatterns.producerAndConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by xuye on 2018年1月7日
 * <p>
 * 生成者、消费者模型<br/>
 * 参考：https://www.jianshu.com/p/a42b89287359<br/>
 * 参考2：https://monkeysayhi.github.io/2017/10/08/Java%E5%AE%
 * 9E%E7%8E%B0%E7%94%9F%E4%BA%A7%E8%80%85-%E6%B6%88%E8%B4%B9%E8%80%85%E6%A8%A1%E
 * 5 % 9 E % 8 B /
 */
public class PAndCTest {
	public static void main(String[] args) {
		BlockingQueue<Product> queue = new LinkedBlockingDeque<Product>(100);
		new Thread(new Runnable() {

			int productId = 0;
			Producer<Product> producer = new Producer<Product>(queue);

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(500);
						producer.put(new Product(productId++));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

		new Thread(new Runnable() {

			Consumer<Product> consumer = new Consumer<Product>(queue);

			@Override
			public void run() {
				while (true) {
					try {
						System.out.println("消费者：消费产品 id=" + consumer.take().mId);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
