package algorithm;

import dataStructure.MyLinkedList;
import dataStructure.Node;

/**
 * Created by xuye on 2017年12月19日
 * <p>
 * You are given two linked lists representing two non-negative numbers. The
 * digits are stored in reverse order and each of their nodes contain a single
 * digit. Add the two numbers and return it as a linked list.<br/>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) <br/>
 * Output: 7 -> 0 -> 8 <br/>
 * 【翻译】<br/>
 * 有两个单链表，代表两个非负数，每一个节点代表一个数位，数字是反向存储的，<br/>
 * 即第一个结点表示最低位，最后一个结点表示最高位。求两个数的相加和，并且以链表形式返回<br/>
 * 【思路】参考：http://blog.csdn.net/jingsuwen1/article/details/51355580<br/>
 */
public class AddTwoNumbersImpl {
	public static Node addTwoNumbers(Node<Integer> first, Node<Integer> second) {
		if (first == null) {
			return second;
		}
		if (second == null) {
			return first;
		}

		int sum = 0;
		int carry = 0;

		Node<Integer> head = new Node<Integer>(0);
		Node<Integer> current = head;

		while (first != null || second != null || carry != 0) {
			int num1 = 0;
			int num2 = 0;
			// 链表1节点还有值，取出值并移动到下一个节点
			if (first != null) {
				num1 = first.element;
				first = first.next;
			}
			// 链表2节点还有值，取出值并移动到下一个节点
			if (second != null) {
				num2 = second.element;
				second = second.next;
			}

			// 就算某个链表没有值了，其他链表还有值或者还有进位就还可以继续执行相加求和
			sum = num1 + num2 + carry;
			carry = sum / 10;

			// temp承载本次相加新生成的节点
			Node<Integer> temp = new Node<Integer>(sum % 10);

			// 将新生成的节点添加到current后面，移动current到下一位
			current.next = temp;
			current = current.next;
		}

		// 返回头结点后的首元节点
		return head.next;

	}

	public static void main(String[] args) throws Exception {
		MyLinkedList list1 = new MyLinkedList();
		list1.add(2);
		list1.add(4);
		// list1.add(3);

		MyLinkedList list2 = new MyLinkedList();
		list2.add(5);
		list2.add(6);
		list2.add(4);
		list2.add(3);

		Node<Integer> sumHead = addTwoNumbers(list1.get(0), list2.get(0));
		System.out.println("----链表相加结果：----");
		StringBuilder builder = new StringBuilder("");
		while (sumHead != null) {
			builder.append(sumHead.element);
			builder.append(" ");
			sumHead = sumHead.next;
		}
		System.out.println(builder.toString());
	}
}
