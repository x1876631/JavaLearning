package algorithm;

/**
 * Created by xuye on 2018年2月27日
 * <p>
 * 求最长升序子序列
 */
public class LISImpl {

	/**
	 * 给定一个整数序列，找到一个最长上升子序列，并输出该子序列的长度，时间复杂度是O(n)<br/>
	 * 原理参考：http://www.cnblogs.com/Qmelbourne/p/5885648.html
	 */
	public static int getLongestSqSize(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int[] dp = new int[array.length];// dp[i]表示第i个元素前，有多少个比第i个元素小的递增元素个数
		int maxSize = 1;// 最长递归子序列的长度
		for (int i = 0; i < array.length; i++) {
			// 从头到尾筛选，为什么要从头到尾找，是因为这是个无序数组，后面的不一定比前面的大
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (array[j] < array[i]) {
					// 1、如果有三个元素，那么如果a[2]>a[0],a[2]>a[1],则a[2]可以作为a[0]或者a[1]所在最长上升子序列的最后一个元素。
					// 那选择哪一个序列就要看a[0],a[1]哪个所在的序列要更长。
					// 2、扩展到n个元素，就是看以a[n]为最后一个元素的最长上升子序列的长度是多少。
					dp[i] = Math.max(dp[i], dp[j] + 1);
					maxSize = Math.max(maxSize, dp[i]);
				}
			}
		}

		return maxSize;
	}

	public static void main(String[] args) {
		int[] array = { 4, 2, 4, 5, 3, 7 };
		System.out.println("最长升序子序列长度：" + getLongestSqSize(array));
	}
}
