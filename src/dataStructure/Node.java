package dataStructure;

/**
 * created by xuye on 2017年12月7日
 * 
 * 链表节点类，就有2个变量，数据域和指针域
 */
public class Node<T> {
	public T element;// 数据域
	public Node next;// 指针域

	public Node(T element) {
		this(element, null);
	}

	public Node(T element, Node next) {
		this.element = element;
		this.next = next;
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	@Override
	public String toString() {
		if (this.element != null) {
			return this.element.toString();
		} else {
			return "null";
		}
	}
}
