package algorithm;

import java.util.Arrays;

/**
 * Created by xuye on 2017年12月18日
 * <p>
 * 排序算法测试类
 */
public class SortTest {

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
	public static void QuickSort(int[] array, int start, int end) {
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
			QuickSort(array, start, i - 1);

			System.out
					.println("\n递归执行大值部分的排序start=" + (i + 1) + ", end=" + end);
			QuickSort(array, i + 1, end);
		}
	}

	public static void main(String[] args) {
		int[] array = new int[] { 3, 0, 1, 8, 7, 2, 5, 4, 9, 6 };
		// int[] array = new int[] { 2, 8, 7 };
		System.out.println("----原始数组----");
		System.out.println(Arrays.toString(array));
		System.out.println("\n----开始排序----");
		QuickSort(array, 0, array.length - 1);
		System.out.println("\n----排序后的数组----");
		System.out.println(Arrays.toString(array));
	}
}
