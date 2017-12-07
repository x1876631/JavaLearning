package dataStructure;

/**
 * created by xuye on 2017年12月7日
 * 
 * 自定义实现的单向链表
 */
public class LinkList implements List {

	private Node mHead;// 头指针
	private Node mCurrent;// 当前要操作的节点的指针
	private int mSize;// 列表元素个数

	public LinkList() {
		this.mHead = mCurrent = new Node(null);
		this.mSize = 0;
	}

	/**
	 * 定位函数，要插入a2节点前，就要定位到a2的前一个节点
	 * 
	 * @throws Exception
	 *             Exception
	 */
	private void index(int index) throws Exception {
		if (index < -1 || index > mSize - 1) {
			throw new Exception("定位index异常");
		}

		// 第一个普通节点的下标是0，头结点下标就是-1
		if (index == -1) {
			return;
		}
		// 当前操作节点从第一个节点开始
		mCurrent = mHead.getNext();
		int j = 0;
		// 从首元节点遍历到指定的index那个节点，这就是链表查询效率低下的原因，每次都需要遍历
		while (mCurrent != null && j < index) {
			mCurrent = mCurrent.getNext();
			j++;
		}
	}

	@Override
	public void insert(int index, Object object) throws Exception {
		if (index < 0 || index > mSize) {
			throw new Exception("插入位置异常");
		}
		// 先指向到要操作的节点的前一个节点
		index(index - 1);
		// 创建一个节点，并让当前节点指向该新建节点
		mCurrent.setNext(new Node(object, mCurrent.getNext()));
		mSize++;
	}

	@Override
	public void delete(int index) throws Exception {
		if (isEmpty()) {
			throw new Exception("链表为空，无法删除");
		}
		if (index < 0 || index > mSize) {
			throw new Exception("删除位置异常");
		}
		// 找到要删除的那个节点的前一个节点
		index(index - 1);
		// 让前一个节点的指针指向当前节点的下一个节点
		mCurrent.setNext(mCurrent.getNext().getNext());
		mSize--;
	}

	@Override
	public Object get(int index) throws Exception {
		if (index < 0 || index > mSize) {
			throw new Exception("插入位置异常");
		}
		index(index);
		return mCurrent.getElement();
	}

	@Override
	public int size() {
		return this.mSize;
	}

	@Override
	public boolean isEmpty() {
		return this.mSize == 0;
	}

}
