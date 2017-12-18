package algorithm;

import java.util.Arrays;

/**
 * Created by xuye on 2017年12月18日
 * <p>
 * TopK问题，解法1：使用最小堆，思路参考：http://lib.csdn.net/article/datastructure/54773<br/>
 * 数据量比较大（特别是大到内存不可以容纳）时，偏向于采用堆<br/>
 * 思路：<br/>
 * 1、在n个数里找最大的m个数，先从n个数里取前m个数生成一个最小堆，堆的根节点是这个堆里最小的数，<br/>
 * 2、然后从m+1个数开始，不断取值和最小堆的根节点比较，如果取值<=根节点值，就丢弃；如果>根节点值，就替代根节点值，<br/>
 * 3、然后重新调整堆，使根节点仍然是最小值，重复上诉步骤<br/>
 */
public class TopKByHeap {

	/**
	 * 根据源数组创建一个元素个数为k的最小堆
	 * 
	 * @param array
	 *            源数组
	 * @param k
	 *            要查找的最大k个数
	 * @return 最小堆
	 */
	public int[] buildHeap(int[] array, int k) {
		// 获取前k个数组成的数组
		int[] newArray = new int[k];
		for (int i = 0; i < k; i++) {
			newArray[i] = array[i];
		}
		// 通过上述数组，来创建一个最小堆。
		int parentIndex = getParentIndex(k - 1);
		for (int j = parentIndex; j >= 0; j--) {
			// 创建堆的基本思路是先找到最后一个节点，从他的父节点开始，不断调整，一直到根节点
			// 图解流程参考：http://blog.csdn.net/hrn1216/article/details/51465270
			percolateDown(j, newArray);
		}
		return newArray;
	}

	/**
	 * 下滤调整，从index开始的节点，都和其下面的子节点比较，调整3者的位置
	 */
	public void percolateDown(int index, int[] newArray) {
		int left = getLeftChildIndex(index);
		int right = getRightChildIndex(index);
		int min = index;
		// 找3者中最小的那个节点，然后把最小的节点和根节点互换
		if (right < newArray.length && newArray[right] < newArray[min]) {
			min = right;
		}
		if (left < newArray.length && newArray[left] < newArray[min]) {
			min = left;
		}
		if (min != index) {
			// 节点有调整，交换节点对应的数据
			swap(min, index, newArray);
			// 继续向下调整
			percolateDown(min, newArray);
		}
	}

	public void swap(int a, int b, int[] newArray) {
		int temp = newArray[a];
		newArray[a] = newArray[b];
		newArray[b] = temp;
	}

	public int getLeftChildIndex(int index) {
		return index * 2 + 1;
	}

	public int getRightChildIndex(int index) {
		return index * 2 + 2;
	}

	public int getParentIndex(int index) {
		return (index - 1) / 2;
	}

	/**
	 * 向最小堆里插入新数据，这个插入的newValue在外部保证是比最小堆的根节点大的值<br/>
	 * 思路：<br/>
	 * 先把根节点替换掉，然后不断下滤比较，调整堆结构就好
	 * 
	 * @param newArray
	 *            已经构建好的最小堆
	 * @param newValue
	 *            新的最小值
	 */
	public void insert(int[] newArray, int newValue) {
		System.out.println("----插入新的大值：" + newValue + "----");
		newArray[0] = newValue;
		int start = 0;
		percolateDown(start, newArray);
	}

	public static void print(int[] data) {
		System.out.println(Arrays.toString(data));
	}

	/**
	 * 在input数组中，找到最大的k个数
	 * 
	 * @return 最大的k个数组成的数组
	 */
	public int[] getTopK(int[] input, int k) {
		int[] result = buildHeap(input, k);
		System.out.println("----打印生成的最小堆----");
		print(result);

		System.out.println("----从源数组里找最大的k个数----");
		for (int i = k; i < input.length; i++) {
			if (input[i] > result[0]) {
				// 如果后续查找的值比最小堆里的值大，再插入，否则都丢弃掉。这样循环结束后，最小堆里的k个数就是要找的k个最大值
				insert(result, input[i]);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int a[] = { 22, 66, 88, 33, 0, 11, 44, 99, 55, 77 };
		int result[] = new TopKByHeap().getTopK(a, 6);
		print(result);
	}
}
