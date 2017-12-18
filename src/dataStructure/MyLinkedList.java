package dataStructure;

/**
 * created by xuye on 2017年12月7日
 * 
 * 自定义实现的单向链表
 */
public class MyLinkedList implements ListInterface {

	private Node mHead;// 头节点，不算在列表元素里
	private Node mCurrent;// 当前要操作的节点的指针
	private int mSize;// 列表元素个数

	public MyLinkedList() {
		this.mHead = mCurrent = new Node(null);
		this.mSize = 0;
	}

	/**
	 * 设置首元节点
	 * 
	 * @param node
	 *            新的首元节点
	 */
	public void setFirstNode(Node node) {
		this.mHead.setNext(node);
	}

	@Override
	public void add(Object object) {
		mCurrent.setNext(new Node(object));
		mCurrent = mCurrent.getNext();
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

	@Override
	public Node get(int index) throws Exception {
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
		StringBuilder string = new StringBuilder();
		if (mHead == null) {
			return "this LinkedList is null";
		}
		mCurrent = mHead.getNext();
		while (mCurrent != null) {
			string.append(mCurrent.getElement().toString() + "->");
			mCurrent = mCurrent.getNext();
		}
		return string.toString();
	}

}
