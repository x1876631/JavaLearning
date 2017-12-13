package dataStructure;

/**
 * Created by xuye on 2017年12月12日
 * <p>
 * 队列的抽象操作接口，常用操作有入队，出队，获取头元素，获取队列元素个数，判断是否为空，判断是否已满
 */
public interface QueueInterface<T> {
	int size();

	int elementCount();

	boolean isEmpty();

	boolean isFull();

	/**
	 * 添加一个元素并返回true，顺带一提，
	 * 
	 * @param t
	 *            要添加的元素
	 * @return 如果队列已满，则返回false
	 */
	boolean offer(T t);

	// /**
	// * add方法和offer方法一样，只是队列已满时，会抛异常
	// *
	// * @param t
	// * @return
	// */
	// boolean add(T t);

	/**
	 * 移除并返问队列头部的元素
	 * 
	 * @return 如果队列为空，则返回null
	 */
	T poll();

	// /**
	// * remove方法和poll方法一样，只是队列未空时，会抛异常
	// *
	// * @return
	// */
	// T remove();

	/**
	 * 返回队列头部的元素
	 * 
	 * @return 如果队列为空，则返回null
	 */
	T peek();

	// /**
	// * element方法和peek方法一样，只是队列为空时，会抛异常
	// *
	// * @return
	// */
	// T element();

	// void clearQueue();
}
