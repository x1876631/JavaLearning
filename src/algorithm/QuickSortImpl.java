package algorithm;

import java.util.Arrays;

/**
 * Created by xuye on 2017年12月19日
 * <p>
 * 快速排序实现类
 */
public class QuickSortImpl {

	/**
	 * 快速排序<br/>
	 * 参考1：http://blog.csdn.net/cruise_h/article/details/19825953<br/>
	 * 参考2：参考：http://blog.csdn.net/collonn/article/details/17886715<br/>
	 * 
	 * @param array
	 *            待排序的源数组
	 * @param start
	 *            排序区间起始index
	 * @param end
	 *            排序区间结尾index
	 */
	public static void quickSort1(int[] array, int start, int end) {
		if (start < end) {
			// key为快排判断基元
			int key = array[start];

			// 从基元处开始判断
			int i = start;
			int j;
			for (j = start + 1; j <= end; j++) {
				System.out.println("start=" + start + ", j=" + j + ", i=" + i
						+ ", end =" + end);
				// 从基元开始，不断和接下来的元素比较，
				// 如果接下来的元素小，就把<=key的元素扔到【start~i】这个区间内，把>key的元素留在【i+1~j】这个区间
				if (array[j] < key) {
					// 交换了a[j]和a[i+1]
					System.out.println("array[j]:" + array[j]
							+ " 和 array[i+1]:" + array[i + 1]);
					int temp = array[j];
					array[j] = array[i + 1];
					array[i + 1] = temp;
					i++;
					System.out.println("交换array[j]和array[i+1]后，i=" + i);
				}
			}
			System.out.println("----交换基元前数组数据：----");
			System.out.println(Arrays.toString(array));

			array[start] = array[i];
			array[i] = key;

			System.out.println("----交换基元后数组数据：----");
			System.out.println(Arrays.toString(array));
			// 递归再去分区排序
			System.out.println("\n递归执行小值部分的排序：start=" + start + ", end="
					+ (i - 1));
			quickSort1(array, start, i - 1);

			System.out
					.println("\n递归执行大值部分的排序start=" + (i + 1) + ", end=" + end);
			quickSort1(array, i + 1, end);
		}
	}

	/**
	 * 参考大话数据结构的讲解实现的快排
	 */
	public static void quickSort2(int[] array, int left, int right) {
		if (left < right) {
			// 获取本次分组后的基准值，对划分出来的左右2组递归排序
			int pivotIndex = partition(array, left, right);
			// 左边都是<=pivot的值，排序范围是left~pivot-1
			quickSort2(array, left, pivotIndex - 1);
			// 右边都是>=pivot的值，排序范围是pivot+1~right
			quickSort2(array, pivotIndex + 1, right);
		}
	}

	/**
	 * 快速排序的关键方法，分区<br/>
	 * 过程就是：获取基准值，并以基准值为标准不断排序，划分出左右2组，最终找到适合基准值的位置并返回
	 * 
	 * @param array
	 *            原始数组
	 * @param left
	 *            数组排序起始下标
	 * @param right
	 *            数组排序终止下标
	 * @return 基准值最终位置下标
	 */
	public static int partition(int[] array, int left, int right) {
		// 使用待排序范围的第一个元素作为基准值
		int pivot = array[left];

		// 循环遍历整个待排序范围内的数据，直到左右指针相交
		while (left < right) {
			// 从排序终点开始向起始点遍历，如果比基准值大，就继续，否则停止
			while (left < right && array[right] >= pivot) {
				right--;
			}
			// 如果从右向左遍历时发现了比基准值小的值，退出遍历循环，交换基准值(array[left])和较小值(array[right])
			swap(array, left, right);
			// 交换后，反方向从左向右遍历
			while (left < right && array[left] <= pivot) {
				left++;
			}
			// 如果从左到右遍历时，遇到比基准值大的值时，交换基准值(array[right])和较大值(array[left])
			swap(array, left, right);
		}
		return left;
	}

	public static void swap(int[] array, int x, int y) {
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}

	public static void main(String[] args) {
		int[] array = new int[] { 3, 0, 1, 8, 7, 2, 5, 4, 9, 6 };
		System.out.println("----原始数组----");
		System.out.println(Arrays.toString(array));
		System.out.println("\n----开始排序----");
		quickSort2(array, 0, array.length - 1);
		System.out.println("\n----排序后的数组----");
		System.out.println(Arrays.toString(array));
	}
}
