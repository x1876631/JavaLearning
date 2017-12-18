package dataStructure;

import java.util.Arrays;

/**
 * Created by xuye on 2017年12月15日
 * <p>
 * 最大堆的实现<br/>
 * 参考：http://vickyqi.com/2015/11/19/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%
 * 9E%84%E7%B3%BB%E5%88%97%E2%80%94%E2%80%94%E5%A0%86/
 */
public class MaxHeap extends Heap {

	public MaxHeap(int[] data) {
		super(data);
	}

	/**
	 * 堆构建<br/>
	 * 参考：http://blog.csdn.net/hrn1216/article/details/51465270<br/>
	 */
	@Override
	public Heap buildHeap() {
		// 思路是，从数组的最后一个节点开始，循环调整，直到根节点
		// 为什么要循环调整？之所以要循环调整，是因为第一次构建堆的时候，整个堆里所有节点都不是有序的，需要把每个节点都遍历一遍排好序
		// 而插入节点，只要从下到上该对应修改的节点就好了，不用调整其他已有序的节点

		// 拿到最后一个节点的父节点
		int start = getParentIndex(length - 1);
		// 不断调整节点及其以下的节点
		for (; start >= 0; start--) {
			adjustDownHeap(start);
		}
		return this;
	}

	/**
	 * 自上而下的调整节点
	 */
	@Override
	public void adjustDownHeap(int currentNodeIndex) {
		int right = getRightChildIndex(currentNodeIndex);// 当前节点右孩子的下标
		int left = getLeftChildIndex(currentNodeIndex);// 当前节点左孩子的下标
		int max = currentNodeIndex;// max表示3个节点里值最大的那个节点的下标
		// 判断当前节点是否需要和左右节点互换
		if (right < length && data[right] > data[max]) {
			max = right;
		}
		if (left < length && data[left] > data[max]) {
			max = left;
		}
		if (max != currentNodeIndex) {
			// max对应的节点发生了变化，则需要互换节点的值，但是存储的数组下标不动
			swap(currentNodeIndex, max);
			// 然后继续调整调整后的小值的后续节点，以便使整个结构在调整节点后仍满足最大堆的逻辑
			adjustDownHeap(max);
		}
	}

	/**
	 * 插入新节点
	 */
	@Override
	public Heap insert(int value) {
		// 新建个数组，给数组扩容
		int[] newData = new int[length + 1];
		System.arraycopy(data, 0, newData, 0, length);
		// 新数组最后一位插入新数据
		newData[length] = value;
		this.data = newData;
		this.length = length + 1;
		// 重新调整整个堆结构，从最后一个节点开始向上调整
		adjustUpHeap(this.length - 1);
		return this;
	}

	/**
	 * 自下而上调整堆结构
	 */
	@Override
	public void adjustUpHeap(int currentNodeIndex) {
		// 拿到当前节点的父节点下标
		int parent = getParentIndex(currentNodeIndex);
		if (parent >= 0 && data[parent] < data[currentNodeIndex]) {
			// 父节点小于当前节点，互换节点的值
			swap(parent, currentNodeIndex);
			// 从互换的parent开始，继续向上调整堆结构，保证上层也是堆
			adjustUpHeap(parent);
		}
	}

	/**
	 * 删除节点，通常删除都是根节点
	 */
	@Override
	public Heap remove() {
		// 交换头尾节点的值
		swap(0, length - 1);
		// 复制少一个元素的数组，由于已经把头节点调到了尾节点，去掉了一个元素，相当于删除了头节点
		int[] newData = new int[length - 1];
		System.arraycopy(data, 0, newData, 0, length - 1);
		this.data = newData;
		this.length = length - 1;
		// 从头开始，重新调整堆结构
		adjustDownHeap(0);
		return this;
	}

	public static void main(String[] args) {
		int[] data = new int[] { 31, 39, 51, 79, 25, 8, 33, 75, 60 };

		System.out.println("----原始数组----");
		System.out.println(Arrays.toString(data));
		System.out.println("----最大堆构建完成----");
		Heap heap = new MaxHeap(data);
		heap.buildHeap().print();
		System.out.println("----删除最大节点----");
		heap.remove().print();
		System.out.println("----插入新节点：90----");
		heap.insert(90).print();
	}
}
