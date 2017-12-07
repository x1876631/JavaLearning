package dataStructure;

/**
 * created by xuye on 2017年12月8日
 * 
 * 自定义链表测试类
 */
public final class LinkListTest {
	public static void main(String[] args) throws Exception {
		LinkList list = new LinkList();
		for (int i = 0; i < 10; i++) {
			// 依次输入10个0-99的随机数
			int temp = (int) (Math.random() * 100) % 100;
			list.insert(i, temp);
			System.out.println("插入值：" + temp);
		}

		list.delete(4);
		System.out.println("----链表删除第5个元素----");
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
	}
}
