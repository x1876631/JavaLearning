package algorithm;

import java.util.Arrays;

/**
 * created by xuye on 2018年2月25日
 * 
 * 求2个升序数组的中位数<br/>
 * 原理：奇数个数的数组，中位数是第n/2个数，偶数个数的数组，中位数是(n/2+n/2-1)/2的值<br/>
 * 各种完善的解法思路：http://www.cnblogs.com/zichi/p/leetcode-4-median-of-two-sorted-
 * arrays. html<br/>
 * 简单的解法：归并排序，时间复杂度O(nlogn)，参考：http://blog.csdn.net/liyinan11/article/details/
 * 66973655
 */
public class FindTwoArrayMedianImpl {

	/**
	 * 多路归并2个数组，再找中位数
	 */
	public static double findByMerge(int[] a1, int[] a2) {
		int[] result = new int[a1.length + a2.length];

		// 记录2个数组取值取到哪里了
		int a1Index = 0;
		int a2Index = 0;

		int resultIndex = 0;
		// 只要还没取完，就一直取值，直到2个数组所有值都取完为止
		while (a1Index < a1.length || a2Index < a2.length) {
			// 2个数组还有值
			if (a1Index < a1.length && a2Index < a2.length) {
				if (a1[a1Index] <= a2[a2Index]) {
					result[resultIndex++] = a1[a1Index++];
				} else {
					result[resultIndex++] = a2[a2Index++];
				}
			} else if (a1Index < a1.length) {
				// 只有第一个数组有值
				result[resultIndex++] = a1[a1Index++];
			} else if (a2Index < a2.length) {
				// 只有第2个数组有值
				result[resultIndex++] = a2[a2Index++];
			}
		}
		System.out.println("合并后的数组：" + Arrays.toString(result));

		double median = -1;
		// 合并完了数组，找中位数
		if (result.length % 2 != 0) {
			// 数组个数是奇数
			median = result[result.length / 2];
		} else {
			// 数组个数是偶数
			median = ((double) (result[result.length / 2] + result[result.length / 2 - 1])) / 2.0;
		}
		return median;
	}

	public static void main(String[] args) {
		int[] array1 = { 1, 3, 5, 7 };
		int[] array2 = { 2, 4 };
		System.out.println("2个数组的中位数是：" + findByMerge(array1, array2));
	}
}
