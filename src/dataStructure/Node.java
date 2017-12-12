package dataStructure;

/**
 * created by xuye on 2017年12月7日
 * 
 * 链表节点类，就有2个变量，数据域和指针域
 */
public class Node<T> {
	private T mElement;// 数据域
	private Node mNext;// 指针域

	public Node(T element) {
		this(element, null);
	}

	public Node(T element, Node next) {
		this.mElement = element;
		this.mNext = next;
	}

	public T getElement() {
		return mElement;
	}

	public void setElement(T element) {
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
		if (this.mElement != null) {
			return this.mElement.toString();
		} else {
			return "null";
		}
	}
}
