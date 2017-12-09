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
		// loopReverse(list);
		list.setFirstNode(recursiveReverse(list.get(0)));
		System.out.println("\n----链表反转后：" + list.toString() + "----");
	}

	/**
	 * 递归实现反转链表，实现的比较巧妙，一开始有点难以理解<br/>
	 * 可以参考链接文章，看看图： http://blog.csdn.net/fx677588/article/details/72357389<br/>
	 * 简单来说就是递归到最后一个节点，从最后一个节点开始修改next实现反转，然后递归返回继续反转前面的节点，直到回到原本的第一个节点，变为了尾节点
	 * 
	 * @param head
	 *            要反转的节点的前一个节点
	 * @return Node 反转后的首节点
	 */
	public static Node recursiveReverse(Node head) {
		if (head == null || head.getNext() == null) {
			// 递归到最后一个了，返回这个节点去反转
			return head;
		}
		// newHead没变过，一直指向原本链表最后一个节点，该节点反转之后变为新首节点
		Node newHead = recursiveReverse(head.getNext());

		// head递归到最后，一定会有个next，而不是最后一个节点，因为上面判断了
		// head.getNext().getNext()==null才返回的，所以最后一次递归里head.getNext才是最后一个节点，head是前一个节点
		Node nextNode = head.getNext();// 存储下一个节点，用于反转

		// 下面这句就是递归反转算法的关键步骤
		nextNode.setNext(head);// 开始反转节点。最后一次递归时，nextNode就是原本的尾节点，变成了首节点，并从这个节点开始反转

		System.out.println("current：" + head + " ， current.next：" + nextNode + " ， next.next：" + nextNode.getNext());
		head.setNext(null);// 前节点置空。这个置空很有必要，不置空的话会导致环链，原来的首节点无法变成尾节点

		// 返回反转后的新的首节点
		return newHead;
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
