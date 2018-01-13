package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xuye on 2018年1月13日
 * <p>
 * 给定一个n个元素的数组。是否存在a，b，c三个元素。使用得a+b+c=0，找出全部符合这个条件的三元组。<br/>
 * 参考：https://www.cnblogs.com/gavanwanggw/p/7253591.html
 */
public class Sum3 {

	public static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		if (nums != null && nums.length > 2) {
			// 先排序，默认从小到大排序
			Arrays.sort(nums);
			for (int firstIndex = 0; firstIndex < nums.length - 2;) {
				// 第2个数的起始位置
				int secondIndex = firstIndex + 1;
				// 第3个数的结束位置
				int thirdIndex = nums.length - 1;
				while (secondIndex < thirdIndex) {
					if (nums[secondIndex] + nums[thirdIndex] == -nums[firstIndex]) {
						// 找到了符合要求的三个数，加到集合里
						List<Integer> list = new ArrayList<Integer>();
						list.add(nums[firstIndex]);
						list.add(nums[secondIndex]);
						list.add(nums[thirdIndex]);
						result.add(list);

						// 同时移动second和third
						secondIndex++;
						thirdIndex--;

						// 跳过重复的元素
						while (secondIndex < thirdIndex
								&& nums[secondIndex] == nums[secondIndex - 1]) {
							secondIndex++;
						}
						while (secondIndex < thirdIndex
								&& nums[thirdIndex] == nums[thirdIndex + 1]) {
							thirdIndex--;
						}
					} else if (nums[secondIndex] + nums[thirdIndex] > -nums[firstIndex]) {
						// 3数之和>0，从右向左移动指针thirdIndex，缩小范围
						// 【注意！】之所以能这么移动指针，是因为数组已经从小到大排过序了，右边的元素>=左边的元素
						thirdIndex--;
						while (secondIndex < thirdIndex
								&& nums[thirdIndex] == nums[thirdIndex + 1]) {
							// 跳过重复的元素
							thirdIndex--;
						}
					} else {
						// 3数之和<0，从左向右移动指针，缩小范围
						secondIndex++;
						while (secondIndex < thirdIndex
								&& nums[secondIndex] == nums[secondIndex - 1]) {
							secondIndex++;
						}
					}
				}
				// 一个while循环走完，能匹配firstIndex的2个数都排查遍了，继续从下一个数开始匹配
				firstIndex++;
				while (firstIndex < nums.length - 2
						&& nums[firstIndex] == nums[firstIndex - 1]) {
					// 跳过重复的元素
					firstIndex++;
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] array = { -1, 0, 1, 2, -1, -4 };
		System.out.println("----源数组元素：" + Arrays.toString(array) + "----");
		List<List<Integer>> result = threeSum(array);
		System.out.println("----符合3数之和的数组----");
		for (List<Integer> list : result) {
			for (int i : list) {
				System.out.print(i + " ");
			}
			System.out.println("");
		}
	}

	public static void testSort() {
		int[] array = { -1, 0, 1, 2, -1, -4 };
		Arrays.sort(array);
		System.out.println(Arrays.toString(array));
	}
}
