package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuye on 2018年1月13日
 * <p>
 * 多路归并，合并K个长度为n的有序链表，返回合并后的链表<br/>
 * 参考：http://shmilyaw-hotmail-com.iteye.com/blog/1776132<br/>
 * 话说这不和求topk问题差不多吗？要么用合并法{@link TopKByMerge}，要么用堆{@link TopKByHeap}
 */
public class MergeKList {
	/**
	 * 合并法求topk<br/>
	 * a[5,3,2,1],b[7,4,3,1] -> result[7,5,4]
	 * 
	 * @param input
	 * @param k
	 * @return
	 */
	public static int[] getTopK(List<List<Integer>> input, int k) {
		// 保存每个数组已经扫描到该数组的第几个元素了，比如index[1]=2，说明第二个数组已经扫描完该数组的第2个元素了
		int[] index = new int[input.size()];
		// 用result承载最大的k个数
		int[] result = new int[k];

		for (int i = 0; i < k; i++) {
			int max = Integer.MIN_VALUE;
			int maxIndex = 0;

			// 挨个从每个排好序的数组里取没扫描过的最大值，即list.get(index[j])，给max
			for (int j = 0; j < input.size(); j++) {
				// list是已经排好序的一个元素集
				List<Integer> list = input.get(j);

				if (index[j] < list.size()) {
					if (max < list.get(index[j])) {
						max = list.get(index[j]);
						maxIndex = j;
					}
				}
			}
			if (max == Integer.MIN_VALUE) {
				return result;
			}
			result[i] = max;
			index[maxIndex] += 1;
		}
		return result;
	}

	public static void main(String[] args) {
		List<Integer> a = new ArrayList<Integer>();
		a.add(5);
		a.add(3);
		a.add(2);
		a.add(1);

		List<Integer> b = new ArrayList<Integer>();
		b.add(7);
		b.add(4);
		b.add(3);
		b.add(1);

		List<List<Integer>> ab = new ArrayList<List<Integer>>();
		ab.add(a);
		ab.add(b);
		int r[] = getTopK(ab, 8);
		for (int i = 0; i < r.length; i++) {
			System.out.print(r[i] + " ");
		}
	}
}
