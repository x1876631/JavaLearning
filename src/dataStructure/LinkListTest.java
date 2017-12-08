package dataStructure;

/**
 * created by xuye on 2017年12月8日
 * 
 * 自定义链表测试类
 */
public final class LinkListTest {
	public static void main(String[] args) throws Exception {
		MyLinkedList list = new MyLinkedList();
		for (int i = 0; i < 10; i++) {
			// 依次输入10个0-99的随机数
			int temp = (int) (Math.random() * 100) % 100;
			list.add(temp);
			System.out.println("插入值：" + temp);
		}

		// list.delete(4);
		// System.out.println("----链表删除第5个元素----");
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i).toString() + " ");
		}
	}
}
