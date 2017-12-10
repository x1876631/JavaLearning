package dataStructure;

/**
 * created by xuye on 2017年12月10日
 * 
 * 双向链表用的节点
 */
public class DoubleNode {
	public Object data;
	public DoubleNode next;
	public DoubleNode prev;

	public DoubleNode() {
		this(null, null, null);
	}

	public DoubleNode(Object data, DoubleNode prev, DoubleNode next) {
		this.data = data;
		this.prev = prev;
		this.next = next;
	}

	@Override
	public String toString() {
		if (this.data != null) {
			return this.data.toString();
		} else {
			return "null";
		}
	}
}
