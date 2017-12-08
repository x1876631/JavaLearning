package dataStructure;

/**
 * created by xuye on 2017年12月7日
 * 
 * 自定义实现的单向链表
 */
public class MyLinkedList implements List {

	private Node mHead;// 头节点
	private Node mCurrent;// 当前要操作的节点的指针
	private int mSize;// 列表元素个数

	public MyLinkedList() {
		this.mHead = mCurrent = new Node(null);
		this.mSize = 0;
	}

	public void setHeadNext(Node node) {
		this.mHead.setNext(node);
	}

	@Override
	public void add(Object object) {
		if (mHead == null) {
			// 没有头结点就初始化头结点
			mHead = new Node(object);
			mCurrent = mHead;
		} else {
			// 有了头结点就去正常添加普通节点，并指向当前新操作的结果节点
			mCurrent.setNext(new Node(object));
			mCurrent = mCurrent.getNext();
		}
		mSize++;

	}

	/**
	 * 定位函数
	 * 
	 * @param index
	 *            当前要操作的节点坐标
	 * @throws Exception
	 *             Exception
	 */
	private void index(int index) throws Exception {
		if (index < 0 || index > mSize) {
			throw new Exception("定位index异常");
		}

		mCurrent = mHead.getNext();
		int i = 0;
		// 从头节点遍历到指定的index那个节点，这就是链表查询效率低下的原因，每次都需要遍历
		while (mCurrent != null && i < index) {
			mCurrent = mCurrent.getNext();
			i++;
		}
	}

	// @Override
	// public void insert(int index, Object object) throws Exception {
	// if (index < 0 || index > mSize) {
	// throw new Exception("插入位置异常");
	// }
	// // 先指向到要操作的节点的前一个节点
	// index(index - 1);
	// // 创建一个节点，并让当前节点指向该新建节点
	// mCurrent.setNext(new Node(object, mCurrent.getNext()));
	// mSize++;
	// }
	//
	// @Override
	// public void delete(int index) throws Exception {
	// if (isEmpty()) {
	// throw new Exception("链表为空，无法删除");
	// }
	// if (index < 0 || index > mSize) {
	// throw new Exception("删除位置异常");
	// }
	// // 找到要删除的那个节点的前一个节点
	// index(index - 1);
	// // 让前一个节点的指针指向当前节点的下一个节点
	// mCurrent.setNext(mCurrent.getNext().getNext());
	// mSize--;
	// }

	@Override
	public Object get(int index) throws Exception {
		if (index < 0 || index > mSize) {
			throw new Exception("获取位置异常");
		}
		index(index);
		return mCurrent;
	}

	@Override
	public int size() {
		return this.mSize;
	}

	@Override
	public boolean isEmpty() {
		return this.mSize == 0;
	}

	@Override
	public String toString() {
		String string = "";
		for (int i = 0; i < mSize; i++) {
			try {
				string += ((Node) get(i)).getElement() + "->";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return string;
	}

}
