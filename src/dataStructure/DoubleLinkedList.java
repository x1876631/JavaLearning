package dataStructure;

/**
 * created by xuye on 2017年12月10日
 * 
 * 自定义实现的双向链表<br/>
 * 参考文章：http://blog.csdn.net/u010456903/article/details/48950303<br/>
 */
public class DoubleLinkedList {
	public DoubleNode head;// 头结点

	public DoubleLinkedList() {
		head = new DoubleNode();
	}

	/**
	 * 判断链表是否为空
	 * 
	 * @return true 链表为空
	 */
	public boolean isEmpty() {
		return this.head.next == null;
	}

	/**
	 * 读取链表元素个数
	 * 
	 * @return 链表元素个数
	 */
	public int size() {
		int length = 0;
		// 取第一个节点，从头开始遍历
		DoubleNode p = this.head.next;
		while (p != null) {
			length++;
			p = p.next;
		}
		return length;
	}

	/**
	 * 根据输入的节点下标，返回找到的对应节点
	 * 
	 * @param index
	 *            节点在链表里位置
	 * @return index对应的节点
	 */
	public DoubleNode indexNode(int index) {
		if (index < 0 || index > size() - 1) {
			throw new IndexOutOfBoundsException("参数错误，无法定位");
		} else {
			DoubleNode p = this.head.next;
			int i = 0;
			while (p != null) {
				if (index == i) {
					return p;
				}
				i++;
				p = p.next;
			}
		}

		return null;
	}

	/**
	 * 向链表中添加节点
	 * 
	 * @param data
	 *            节点内容
	 */
	public void add(Object data) {
		if (data == null) {
			throw new NullPointerException("data = null");
		}
		DoubleNode newNode = new DoubleNode(data, null, null);
		DoubleNode p = null;
		if (isEmpty()) {
			// 如果链表现在是空的，添加到头节点后面
			head.next = newNode;
			newNode.prev = head;
		} else {
			// 链表非空，添加到最后
			p = indexNode(size() - 1);
			p.next = newNode;
			newNode.prev = p;
		}
	}

	/**
	 * 添加新节点，插入到指定位置(就是插到原insertIndex节点前面)
	 * 
	 * @param insertIndex
	 *            插入位置
	 * @param data
	 *            插入节点数据域
	 */
	public void insert(int insertIndex, Object data) {
		if (data == null) {
			throw new NullPointerException("data = null");
		}
		DoubleNode newNode = new DoubleNode(data, null, null);
		DoubleNode p = null;
		if (isEmpty()) {
			head.next = newNode;
			newNode.prev = head;
		} else {
			p = indexNode(insertIndex);

			// 先改新添加的节点的前后指针
			newNode.prev = p.prev;
			newNode.next = p;

			// 再改原前节点的后指针
			p.prev.next = newNode;

			// 再该原节点的前指针
			p.prev = newNode;
		}
	}

	/**
	 * 删除指定节点
	 * 
	 * @param index
	 *            要删除的节点在链表里的位置
	 */
	public void delete(int index) {
		if (index < 0 || index > size() - 1) {
			throw new IndexOutOfBoundsException("参数错误，无法定位");
		}
		if (isEmpty()) {
			throw new NullPointerException("链表为空");
		} else {
			DoubleNode p = indexNode(index);
			// 修改删除节点的前后指针
			p.prev.next = p.next;
			p.next.prev = p.prev;
		}
	}

	/**
	 * 按next遍历链表，打印所有节点
	 */
	public void displayByNext() {
		DoubleNode p = this.head.next;
		if (p == null) {
			System.out.println("this list is empty");
		} else {
			System.out.println("----按next遍历链表元素----");
			while (p != null) {
				System.out.print(p + "->");
				p = p.next;
			}
			System.out.println();
		}
	}

	/**
	 * 按prev遍历链表，打印所有节点
	 */
	public void displayByPrev() {
		DoubleNode p = this.head.next;
		if (p == null) {
			System.out.println("this list is empty");
		} else {
			System.out.println("----按prev遍历链表元素----");
			p = indexNode(size() - 1);
			while (p != null && p.prev != null) {
				// p.prev=null，说明p是head，不打印head
				System.out.print(p + "->");
				p = p.prev;
			}
			System.out.println();
		}
	}

	/**
	 * 反转当前链表<br/>
	 * 反转双向和单向的不同在于，反转中和最后设置头节点时，都要设置一下prev，思路和反转单向链表一样
	 */
	public void reverseList() {
		DoubleNode head = null;
		DoubleNode current = this.head.next;
		DoubleNode next = null;
		while (current != null) {

			next = current.next;
			current.prev = next;
			current.next = head;

			System.out.println(
					"本次反转后：current：" + current + " ， current.next：" + current.next + " ， current.prev：" + current.prev);
			head = current;
			current = next;
		}
		// 反转结束时，重新设置下头结点
		this.head.next = head;// 头结点指向新的head
		head.prev = this.head;// 新head的前节点指向头结点
	}

	public static void main(String[] args) {
		DoubleLinkedList list = new DoubleLinkedList();
		list.add("a");
		list.add("c");
		list.insert(1, "b");

		// list.displayByNext();
		// list.displayByPrev();

		list.reverseList();

		list.displayByNext();
		list.displayByPrev();
	}
}
