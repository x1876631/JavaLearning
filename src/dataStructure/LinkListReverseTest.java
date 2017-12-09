package dataStructure;

/**
 * Created by xuye on 2017年12月8日
 * <p>
 * 反转单向链表，比如原本链表a->b->c->null，现在变成c->b->a->null <br/>
 * 有多种思路：<br/>
 * 1、把链表拆分成一个数组，直接反转数组，缺点是浪费空间<br/>
 * 2、使用3个指针遍历链表，对节点逐个反转，这个方法比较常见<br/>
 * 3、把2~n个节点插入到第一个节点后面，然后将第一个节点挪到表尾<br/>
 * 4、递归，反转当前节点之前先反转后续节点
 */
public class LinkListReverseTest {
	public static void main(String[] args) throws Exception {
		MyLinkedList list = new MyLinkedList();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		System.out.println("----链表反转前：" + list.toString() + "----\n");
		loopReverse(list);
		System.out.println("\n----链表反转后：" + list.toString() + "----");
	}

	/**
	 * 循环实现反转链表
	 * 
	 * 思路就是用3个节点(前节点，当前节点，后节点)去遍历链表，挨个反转当前节点，然后向后移动前节点和当前节点，进入下次循环
	 * 
	 * @param list
	 *            待反转的列表
	 * @throws Exception
	 *             Exception
	 * 
	 */
	public static void loopReverse(MyLinkedList list) throws Exception {
		System.out.println("----反转链表开始----");

		Node currentNode = (Node) list.get(0); // 当前操作的节点，在这个节点里反转
		Node headNode = null;// 前节点，只是帮助存储反转后的当前节点的
		Node nextNode = null;// 后节点，就是用来存后节点的，不负责反转，反转都靠移动head和current

		int count = 0;

		// 每个循环只反转一个节点，就是当前节点，然后移动前节点和当前节点以便执行下次循环
		while (currentNode != null) {
			// 1、保存下next，方便让current移动到这个节点
			nextNode = currentNode.getNext();
			// 2、反转current节点，让他指向前节点
			currentNode.setNext(headNode);

			count++;
			System.out.println("第次" + count + "循环，current：" + currentNode + " ， current.next：" + currentNode.getNext()
					+ " ， next：" + nextNode);

			// 3、向后移动前节点
			headNode = currentNode;
			// 4、向后移动当前节点
			currentNode = nextNode;
		}

		// 反转完重新设置一下新的头节点
		list.setFirstNode(headNode);
	}
}
