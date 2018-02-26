package algorithm;

import java.util.Arrays;

/**
 * created by xuye on 2018年2月25日
 * 
 * 求2个升序数组的中位数<br/>
 * 原理：奇数个数的数组，中位数是第n/2个数，偶数个数的数组，中位数是(n/2+n/2-1)/2的值<br/>
 * 各种完善的解法思路：http://www.cnblogs.com/zichi/p/leetcode-4-median-of-two-sorted-
 * arrays. html<br/>
 * 简单的解法：简单合在一起，再排序，再找，时间复杂度(nlogn)
 * 中等解法：归并后再找，时间复杂度O(n)，参考：http://blog.csdn.net/liyinan11/article/details/
 * 66973655
 */
public class FindTwoArrayMedianImpl {

	/**
	 * 多路归并2个数组，再找中位数，归并一次的时间复杂度是O(m+n)，m、n是2个数组的长度
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
		return getMedian(result);
	}

	/**
	 * 获取数组的中位数
	 */
	private static double getMedian(int[] result) {
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

	/**
	 * 二分法找中位数，时间复杂度是O(logn)<br/>
	 * 原理和实现参考：https://hk029.gitbooks.io/leetbook/content/%
	 * E5%88%86%E6%B2%BB/004.
	 * %20Median%20of%20Two%20Sorted%20Arrays[H]/004.%20Median
	 * %20of%20Two%20Sorted%20Arrays[H].html<br/>
	 */
	public static double findByBinary(int[] nums1, int[] nums2) {
		if (nums1.length == 0)
			return MedofArray(nums2);
		if (nums2.length == 0)
			return MedofArray(nums1);
		int n = nums1.length;
		int m = nums2.length;
		if (n > m) // 保证数组1一定最短
			return findByBinary(nums2, nums1);
		int L1 = 0, L2 = 0, R1 = 0, R2 = 0, c1 = 0, c2 = 0, lo = 0, hi = 2 * n; // 我们目前是虚拟加了'#'所以数组1是2*n+1长度
		while (lo <= hi) // 二分
		{
			c1 = (lo + hi) / 2; // c1是二分的结果
			c2 = m + n - c1;
			L1 = (c1 == 0) ? Integer.MIN_VALUE : nums1[(c1 - 1) / 2];
			R1 = (c1 == 2 * n) ? Integer.MAX_VALUE : nums1[c1 / 2];
			L2 = (c2 == 0) ? Integer.MIN_VALUE : nums2[(c2 - 1) / 2];
			R2 = (c2 == 2 * m) ? Integer.MAX_VALUE : nums2[c2 / 2];

			if (L1 > R2)
				hi = c1 - 1;
			else if (L2 > R1)
				lo = c1 + 1;
			else
				break;
		}
		return (Math.max(L1, L2) + Math.min(R1, R2)) / 2.0;
	}

	private static double MedofArray(int[] nums) {
		if (nums.length == 0) {
			return -1;
		}
		return (nums[nums.length / 2] + nums[(nums.length - 1) / 2]) / 2.0;
	}

	public static void main(String[] args) {
		int[] array1 = { 1, 3, 5, 7 };
		int[] array2 = { 2, 4 };
		System.out.println("归并法：2个数组的中位数是：" + findByMerge(array1, array2));
		System.out.println("二分法：2个数组的中位数是：" + findByBinary(array1, array2));
	}
}
