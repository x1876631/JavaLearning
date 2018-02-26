package algorithm;

import java.util.PriorityQueue;

/**
 * Created by xuye on 2018年2月26日
 * <p>
 * 求一个无序数组的中位数。 如：{2,5,4,9,3,6,8,7,1}的中位数为5。 要求：时间复杂度尽量小
 * 参考：https://www.cnblogs.com/shizhh/p/5746151.html
 */
public class FindArrayMedianImpl {

	/**
	 * 堆排序，时间复杂度是O(nlogn)，每次add/poll操作的时间复杂度是O(logn)<br/>
	 */
	public static double findByMinHeap(int[] array) {
		int heapSize = array.length / 2 + 1;// 如果原数组是奇数，则heapsize数是一半，如果是偶数个，则拿了1半多1个，最后取中位数时需要注意一下
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>(heapSize);
		// 将数组的1/2的值组成一个最小堆
		for (int i = 0; i < heapSize; i++) {
			heap.add(array[i]);
		}

		System.out.println(heap.toString());
		// 然后将剩下的一半元素，挨个和最小堆的堆顶比较，小于堆顶的值丢弃掉，大于堆顶的值则把堆顶抛弃，并插入
		for (int j = heapSize; j < array.length; j++) {
			if (heap.peek() < array[j]) {
				heap.poll();
				heap.add(array[j]);
			}
		}

		// 经过上面操作，就得到了一个长度是(2/n)+1的小顶堆
		if (array.length % 2 != 0) {
			// 原数组是奇数个，则直接取出堆顶，堆顶就是中位数
			return heap.peek();
		} else {
			// 原数组是偶数个，则取出前2个数/2，就是中位数
			return (heap.poll() + heap.peek()) / 2.0;
		}
	}

	/**
	 * 分治法获取中位数，类似快排，但是没有把所有的数据都排序，而是每次舍弃掉一部分数据，时间复杂度平均是O(n)，时间复杂度原理见：https://
	 * www. nowcoder.com/questionTerminal/62343a8ba8894379b8a3e5e30a745ace<br/>
	 * 步骤：<br/>
	 * 任意挑一个元素，以改元素为支点，划分集合为两部分，如果左侧集合长度恰为 (n-1)/2，那么支点恰为中位数。<br/>
	 * 如果左侧长度<(n-1)/2, 那么中位点在右侧，反之，中位数在左侧。 进入相应的一侧继续寻找中位点。
	 */
	public static double findByQuickSort(int[] array) {
		if (array == null || array.length == 0) {
			return -1;
		}
		int leftIndex = 0;
		int rightIndex = array.length - 1;
		int middleIndex = array.length / 2;
		int currentIndex = -1;
		while (currentIndex != middleIndex) {
			// 不断划分，找当前数组的中值
			currentIndex = getMiddleIndex(array, leftIndex, rightIndex);
			if (currentIndex < middleIndex) {
				// 划分的值在中位数index左边，说明中位数在右组里
				leftIndex = currentIndex + 1;
			} else if (currentIndex > middleIndex) {
				// 找到的中止在中位数index右边，说明中位数在左组
				rightIndex = currentIndex - 1;
			} else {
				break;
			}
		}
		if (currentIndex == -1) {
			return -1;
		}

		if (array.length % 2 != 0) {
			// 数组是奇数
			return array[currentIndex];
		} else {
			// 数组是偶数
			return (array[currentIndex] + array[currentIndex - 1]) / 2.0;
		}
	}

	public static int getMiddleIndex(int[] array, int left, int right) {
		// 假定的中值，随便取一个，就直接选leftindex的那个值
		int p = array[left];

		// 根据中值，分成2组，找到中值所在的位置
		while (left < right) {
			while (left < right && array[right] >= p) {
				right--;
			}
			swap(array, left, right);
			while (left < right && array[left] <= p) {
				left++;
			}
			swap(array, left, right);
		}
		return left;
	}

	public static void swap(int[] array, int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}

	public static void main(String[] args) {
		int[] array = { 2, 4, 1, 3 };
		System.out.println("无序数组找中位数，最小堆法：" + findByMinHeap(array));
		System.out.println("无序数组找中位数，分治法：" + findByQuickSort(array));
	}
}
