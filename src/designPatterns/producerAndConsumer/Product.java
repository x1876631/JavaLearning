package designPatterns.producerAndConsumer;

/**
 * Created by xuye on 2018年1月7日
 * <p>
 * 生成者&消费者中的产品
 */
public class Product {
	public int mId;

	public Product(int id) {
		this.mId = id;
	}

	@Override
	public String toString() {
		return "product [id=" + mId + "]";
	}
}
