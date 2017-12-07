package dataStructure;

/**
 * created by xuye on 2017年12月7日
 * 
 * 链表节点类，就有2个变量，数据域和指针域
 */
public class Node {
	private Object mElement;// 数据域
	private Node mNext;// 指针域

	/**
	 * 头节点的构造方法
	 * 
	 * @param next
	 *            下一个节点的地址
	 */
	public Node(Node next) {
		this.mNext = next;
	}

	/**
	 * 普通节点的构造方法
	 * 
	 * @param element
	 *            当前节点的数据域
	 * @param next
	 *            下一个节点的地址
	 */
	public Node(Object element, Node next) {
		this.mElement = element;
		this.mNext = next;
	}

	public Object getElement() {
		return mElement;
	}

	public void setElement(Object element) {
		this.mElement = element;
	}

	public Node getNext() {
		return mNext;
	}

	public void setNext(Node next) {
		this.mNext = next;
	}

	@Override
	public String toString() {
		return this.mElement.toString();
	}
}
