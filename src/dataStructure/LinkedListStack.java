package dataStructure;

/**
 * Created by xuye on 2017年12月12日
 * <p>
 * 
 * @param <T>
 *            元素类型
 */
public class LinkedListStack<T> implements StackInterface<T> {

	private Node<T> mTop;// 栈顶指针
	private int mElementCount;// 当前栈元素个数

	public LinkedListStack() {
		mTop = null;
		mElementCount = -1;
	}

	@Override
	public boolean push(T object) {
		if (isFull()) {
			System.out.println("当前栈已满，元素[" + object.toString() + "]添加失败");
			return false;
		} else {
			// 创建一个新的节点，并让新节点next指向原来的节点，也就是说栈顶元素是链表的首节点
			mTop = new Node(object, mTop);
			mElementCount++;
			return true;
		}
	}

	@Override
	public T pop() {
		if (isEmpty()) {
			System.out.println("当前栈已空");
			return null;
		} else {
			T t = mTop.getElement();
			mTop = mTop.getNext();// 下移指针，指向下面的元素
			mElementCount--;
			return t;
		}
	}

	@Override
	public T peek() {
		if (isEmpty()) {
			return null;
		} else {
			return mTop.getElement();
		}
	}

	@Override
	public boolean isEmpty() {
		return mTop == null;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public int getSize() {
		return getElementSize();
	}

	@Override
	public int getElementSize() {
		return mElementCount;
	}

	public void display() {
		StringBuilder builder = new StringBuilder();
		if (isEmpty()) {
			System.out.println("当前栈为空");
		} else {
			Node<T> current = mTop;
			while (current != null) {
				builder.append(current.getElement() + " ");
				current = current.getNext();
			}
			System.out.println("----打印当前栈内所有元素：----");
			System.out.println(builder.toString());
		}
	}

	public static void main(String[] args) {
		LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
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
