package dataStructure;

/**
 * Created by xuye on 2017年12月12日
 * <p>
 * 自定义实现栈<br/>
 * 栈是后进先出的一种结构，主要有入栈和出栈等操作<br/>
 * 实现参考：<br/>
 * https://segmentfault.com/a/1190000002602101<br/>
 * http://blog.csdn.net/fengyifei11228/article/details/5625961<br/>
 * https://segmentfault.com/a/1190000002602101<br/>
 */
public class MyStack<T> {

	private static final int SIZE = 3;// 栈默认最大深度
	private T[] mArray;
	private int mTop = -1;// 栈顶指针
	private int mMaxSize;

	public MyStack() {
		Object[] array = new Object[SIZE];
		mArray = (T[]) array;
	}

	/**
	 * 指定栈深度的构造方法
	 * 
	 * @param maxSize
	 *            栈最大深度，即允许入栈的最大元素个数
	 */
	public MyStack(int maxSize) {
		Object[] array = new Object[maxSize];
		mArray = (T[]) array;
	}

	public boolean isEmpty() {
		return mTop == -1;
	}

	public boolean isFull() {
		return (mTop + 1) >= mArray.length;
	}

	public int getSize() {
		return mArray.length;
	}

	/**
	 * @return 栈内元素个数
	 */
	public int getStackElementCount() {
		return mTop + 1;
	}

	/**
	 * 入栈操作，添加元素到栈的末尾
	 * 
	 * @param object
	 *            待添加的元素
	 * @throws Exception
	 */
	public boolean push(T object) {
		if (isFull()) {
			System.out.println("当前栈已满，元素[" + object.toString() + "]添加失败");
			return false;
		} else {
			mTop++;
			mArray[mTop] = object;
			return true;
		}
	}

	/**
	 * 出栈操作，将栈中最顶层的元素弹出并将指针下移
	 * 
	 * @return 栈中最顶层的元素
	 */
	public T pop() {
		if (isEmpty()) {
			System.out.println("当前栈已空");
			return null;
		} else {
			T t = mArray[mTop];
			mTop--;
			return t;
		}
	}

	/**
	 * @return 返回当前栈顶元素
	 */
	public T peek() {
		return mArray[mTop];
	}

	public void display() {
		StringBuilder builder = new StringBuilder();
		if (isEmpty()) {
			System.out.println("当前栈为空");
		} else {
			for (int i = 0; i <= mTop; i++) {
				builder.append(mArray[i] + " ");
			}
			System.out.println("----打印当前栈内所有元素：----");
			System.out.println(builder.toString());
		}
	}

	public static void main(String[] args) throws Exception {
		MyStack<Integer> stack = new MyStack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.display();
		System.out.println("打印栈顶元素：" + stack.peek());
		System.out.println("弹出栈顶元素：" + stack.pop());
		stack.display();
	}
}
