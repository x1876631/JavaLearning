package dataStructure;

import java.util.Arrays;

/**
 * Created by xuye on 2017年12月15日
 * <p>
 * 堆数据结构，堆结构是一种完全树，其任意节点都大于或者小于他的子节点，最大值或者最小值为根节点，常见的堆有二叉堆<br/>
 * 参考：http://vickyqi.com/2015/11/19/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%
 * 9E%84%E7%B3%BB%E5%88%97%E2%80%94%E2%80%94%E5%A0%86/<br/>
 */
public abstract class Heap {
	protected int[] data;// 堆里的数据集合
	protected int length = 0;// 堆元素个数

	public Heap(int[] data) {
		this.data = data;
		this.length = data.length;
	}

	/**
	 * 构建堆
	 */
	public abstract Heap buildHeap();

	/**
	 * 删除根节点
	 */
	public abstract Heap remove();

	/**
	 * 插入节点，只能插入打牌最后
	 * 
	 * @param value
	 *            插入的值
	 */
	public abstract Heap insert(int value);

	/**
	 * 从currentNodeIndex节点开始，自上而下的调整堆
	 * 
	 * @param currentNodeIndex
	 */
	public abstract void adjustDownHeap(int currentNodeIndex);

	/**
	 * 从currentNodeIndex节点开始，自下而上的调整堆
	 * 
	 * @param currentNodeIndex
	 */
	public abstract void adjustUpHeap(int currentNodeIndex);

	/**
	 * 交换节点内容
	 * 
	 * @param n1
	 *            节点1
	 * @param n2
	 *            节点2
	 */
	public void swap(int n1, int n2) {
		int temp = data[n1];
		data[n1] = data[n2];
		data[n2] = temp;
	}

	/**
	 * 根据当前节点下标，找到父节点下标
	 * 
	 * @param node
	 *            当前节点
	 * @return 当前节点的父节点下标
	 */
	protected int getParentIndex(int currentNodeIndex) {
		// 在数组中下标为node的元素，其父节点的下标为(node-1)/2
		return (currentNodeIndex - 1) / 2;
	}

	/**
	 * 根据当前节点下标，找到右孩子坐标
	 * 
	 * @param currentNodeIndex
	 *            当前节点下标
	 * @return 当前节点的右孩子坐标
	 */
	protected int getRightChildIndex(int currentNodeIndex) {
		return currentNodeIndex * 2 + 1;
	}

	/**
	 * 根据当前节点下标，找到左孩子坐标
	 * 
	 * @param currentNodeIndex
	 *            当前节点下标
	 * @return 当前节点的左孩子坐标
	 */
	protected int getLeftChildIndex(int currentNodeIndex) {
		return currentNodeIndex * 2 + 2;
	}

	protected void print() {
		System.out.println(Arrays.toString(data));
	}

}
