package algorithm;

import dataStructure.MyLinkedList;
import dataStructure.Node;

/**
 * Created by xuye on 2018年1月13日
 * <p>
 * 单链表输出倒数第 k 个元素<br/>
 * 参考：http://blog.csdn.net/lvsaixia/article/details/40708811<br/>
 * 参考2：http://blog.csdn.net/sgbfblog/article/details/7877527
 */
public class OutputListKImpl {

	/**
	 * 最简单的思路是求链表长度n，然后找到n-k+1的那个节点，但是这遍历一遍链表。<br/>
	 * 这里用一个只遍历一遍的方法，就是同时用2个节点，节点1指着开头的节点，节点2指着第k个节点，<br/>
	 * 然后同时向后移动，当节点2移动到末尾时，节点1就是倒数第k个节点
	 * 
	 * @param head
	 *            链表首元节点
	 * @param k
	 *            倒数第k
	 * @return 倒数第k个节点(即正数第n-k+1的节点)
	 */
	public static Node outputListK(Node head, int k) {
		Node node1 = head;
		Node node2 = head;
		for (; k > 0 && node2 != null; k--) {
			// 将node2指向第k个节点
			node2 = node2.next;
		}
		// 如果链表长度>k，此时k应该是0
		if (k > 0) {
			return null;
		}
		// 同时向后移动节点1和2，直到节点2到末尾，此时节点1距离节点2(即末尾有k个节点)，就是我们要找的倒数第k个节点
		while (node2 != null) {
			node1 = node1.next;
			node2 = node2.next;
		}
		return node1;
	}

	public static void main(String[] args) throws Exception {
		MyLinkedList list = new MyLinkedList();
		list.add(new Node<Integer>(1));
		list.add(new Node<Integer>(2));
		list.add(new Node<Integer>(3));
		list.add(new Node<Integer>(4));
		list.add(new Node<Integer>(5));
		int k = 6;
		Node result = outputListK(list.get(0), k);
		String resultValue = "null";
		if (result != null) {
			resultValue = String.valueOf(result.element);
		}
		System.out.println("链表中倒数第" + k + "个节点值：" + resultValue);

	}
}
