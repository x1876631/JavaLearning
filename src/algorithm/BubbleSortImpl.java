package algorithm;

import java.util.Arrays;

/**
 * created by xuye on 2017年12月20日
 * 
 * 冒泡排序<br/>
 * 思路:不断比较相邻的2个数，如果后面的比前面大，就交换元素值，一次循环下来，a[i]承载本次循环发现的最小值，走array.length个循环<br/>
 * 时间复杂度O(n^2)
 */
public class BubbleSortImpl {
	/**
	 * 最简单易懂的冒泡排序
	 * 
	 * @param array
	 *            待排序的源数组
	 */
	public static void bubbleSortSimple(int[] array) {
		int i, j;
		for (i = 0; i < array.length; i++) {
			for (j = i + 1; j < array.length; j++) {
				if (array[i] > array[j]) {
					swap(array, i, j);
				}
			}
		}
	}

	public static void swap(int[] array, int x, int y) {
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}

	public static void main(String[] args) {
		int[] array = new int[] { 9, 1, 5, 8, 3, 7, 4, 6, 2 };
		System.out.println("----排序前的数组----");
		System.out.println(Arrays.toString(array));
		bubbleSortSimple(array);
		System.out.println("----排序后的数组----");
		System.out.println(Arrays.toString(array));
	}
}
