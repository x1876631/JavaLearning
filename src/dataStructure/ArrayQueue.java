package dataStructure;

import java.util.Arrays;

/**
 * Created by xuye on 2017年12月12日
 * <p>
 * 自定义实现队列，底层使用数组实现<br/>
 * 参考文章：<br/>
 * http://blog.csdn.net/javazejian/article/details/53375004<br/>
 * http://blog.csdn.net/qq_29542611/article/details/52599072<br/>
 */
public class ArrayQueue<T> implements QueueInterface<T> {

	private T[] mArray;// 队列列表
	private int mHead;// 头指针，用于移除元素，所以默认值是0(如果是-1，移除时会找不到元素)
	private int mTail;// 尾指针，用于添加元素，所以默认值是-1(添加完变0)
	private int mMaxSize;// 队列长度
	private int mElementCount;// 队列内元素个数

	public ArrayQueue() {
		this(3);
	}

	@SuppressWarnings("unchecked")
	public ArrayQueue(int maxSize) {
		this.mMaxSize = maxSize;
		this.mArray = (T[]) new Object[maxSize];
		this.mHead = 0;
		this.mTail = -1;
		this.mElementCount = 0;
	}

	public int getHead() {
		return mHead;
	}

	public int getTail() {
		return mTail;
	}

	@Override
	public int size() {
		return mMaxSize;
	}

	@Override
	public int elementCount() {
		return mElementCount;
	}

	@Override
	public boolean isEmpty() {
		return mElementCount == 0;
	}

	@Override
	public boolean isFull() {
		return mElementCount == mArray.length;
	}

	@Override
	public boolean offer(T t) {
		if (isFull()) {
			System.out.println("队列已满，元素\"" + t.toString() + "\"添加失败");
			return false;
		} else {
			// 队列没满，但是尾指针已经到队尾了，则将尾指针循环到头去，从起始位置添加元素
			if (mTail == mMaxSize - 1) {
				mTail = -1;
			}

			// 添加
			mTail++;
			mArray[mTail] = t;
			mElementCount++;
			return true;
		}
	}

	@Override
	public T poll() {
		if (isEmpty()) {
			System.out.println("当前队列为空，无法执行删除操作");
			return null;
		} else {
			// 队列非空，但是头指针又到了末尾，则说明数组前面还有元素，循环回去
			if (mHead == mMaxSize) {
				// 这里为什么条件是==maxsize呢，因为每次移除后head都++，所以在移除了最后一个元素(下标是maxsize-1)后，head的值就变为maxsize了
				mHead = 0;
			}
			mElementCount--;
			T t = mArray[mHead];
			mHead++;
			return t;
		}
	}

	@Override
	public T peek() {
		if (isEmpty()) {
			return null;
		} else {
			return mArray[mHead];
		}
	}

	@Override
	public String toString() {
		return Arrays.toString(mArray);
	}

	public static void main(String[] args) {

		System.out.println("--------------------自定义队列测试--------------------");
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(5);
		System.out.println("刚创建队列时，queue.isEmpty():" + queue.isEmpty()
				+ ", queue.isFull():" + queue.isFull() + ", queue.size():"
				+ queue.size());

		System.out.println("下面添加3个元素：");
		for (int i = 0; i < 3; i++) {
			queue.offer(i);
		}
		System.out.println("queue.getHead():" + queue.getHead()
				+ ", queue.getTail()：" + queue.getTail() + "，queue.size()："
				+ queue.size());
		System.out.println(queue);

		// 干掉元素，元素只是不能访问到了，被干掉的元素可以被添加的覆盖
		System.out.println("下面干掉2个元素：");
		queue.poll();
		queue.poll();
		System.out.println("queue.getHead():" + queue.getHead()
				+ ", queue.getTail()：" + queue.getTail() + "，queue.size()："
				+ queue.size());
		System.out.println(queue);

		// 此时尾指针又要从开始出添加元素
		System.out.println("下面添加4个元素：");
		for (int i = 3; i < 8; i++) {
			queue.offer(i);
		}
		System.out.println("queue.getHead():" + queue.getHead()
				+ ", queue.getTail()：" + queue.getTail() + "，queue.size()："
				+ queue.size());
		System.out.println(queue);

		System.out.println("下面干掉4个元素：");
		queue.poll();
		queue.poll();
		queue.poll();
		queue.poll();
		System.out.println("queue.getHead():" + queue.getHead()
				+ ", queue.getTail()：" + queue.getTail() + "，queue.size()："
				+ queue.size());
		System.out.println(queue);
	}

}
