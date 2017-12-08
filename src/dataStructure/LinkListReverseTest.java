package dataStructure;

/**
 * Created by xuye on 2017年12月8日
 * <p>
 * 翻转单向列表的实现<br/>
 * 有多种方法：<br/>
 * 1、把链表拆分成一个数组，翻转数组，缺点是浪费空间<br/>
 * 2、使用3个指针遍历链表，对节点逐个翻转<br/>
 * 3、把2~n个节点插入到第一个节点后面，然后将第一个节点挪到表尾<br/>
 * 4、递归，翻转当前节点之前先翻转后续节点(其实链表相当于一个永远只有左树或者右树的二叉树)
 */
public class LinkListReverseTest {
	public static void main(String[] args) throws Exception {
		MyLinkedList list = new MyLinkedList();
		list.add("a");
		list.add("b");
		list.add("c");
		// list.add("d");
		System.out.println(list.toString());
		reverseList(list);
		System.out.println(list.toString());
	}

	public static void reverseList(MyLinkedList list) throws Exception {

		Node currentNode = (Node) list.get(0); // 当前操作的节点
		Node headNode = null;// 新的首节点
		Node nextNode = null;// 当前操作节点的下个节点

		if (currentNode != null) {
			// 1、拿next
			nextNode = currentNode.getNext();
			// 2、反转current
			currentNode.setNext(headNode);
			// 3、存一下已反转的当前节点
			headNode = currentNode;
			// 4、让当前节点指向未反转的next
			currentNode = nextNode;
		}

		// 反转完重新设置一下头节点
		list.setHeadNext(headNode);
	}
}
