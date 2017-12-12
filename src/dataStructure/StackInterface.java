package dataStructure;

/**
 * Created by xuye on 2017年12月12日
 * <p>
 * 栈结构通用抽象操作，
 */
public interface StackInterface<T> {

	public static final int SIZE = 3;// 栈默认最大深度

	/**
	 * 入栈
	 * 
	 * @param object
	 * @return
	 */
	boolean push(T object);

	/**
	 * 出栈
	 * 
	 * @return
	 */
	T pop();

	/**
	 * 获取栈顶元素
	 * 
	 * @return
	 */
	T peek();

	/**
	 * 栈是否为空
	 * 
	 * @return
	 */
	boolean isEmpty();

	/**
	 * 栈是否已满
	 * 
	 * @return
	 */
	boolean isFull();

	/**
	 * 获取栈完整深度
	 * 
	 * @return
	 */
	int getSize();

	/**
	 * 获取栈当前深度
	 * 
	 * @return
	 */
	int getElementSize();
}
