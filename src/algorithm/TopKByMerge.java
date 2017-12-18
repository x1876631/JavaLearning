package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuye on 2017年12月18日
 * <p>
 * TopK问题，解法2：合并法<br/>
 * 已知几个递减有序的m个数组，求这些数据里最大的前k个数，例如：a[5,3,2,1],b[7,4,3,1] -> result[7,5,4]<br/>
 * 思路参考：http://lib.csdn.net/article/datastructure/54773<br/>
 * 设定一个数组下标扫描位置记录临时数组和top结果数组，然后从临时数组记录下标开始遍历所有数组并比较大小，<br/>
 * 将最大值存入结果数组，最大值对应所在数组下标加一存入临时数组，以使其从下位开始遍历，时间复杂度为O(k*m)
 */
public class TopKByMerge {

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
		// b.add(3);
		// b.add(1);
		List<List<Integer>> ab = new ArrayList<List<Integer>>();
		ab.add(a);
		ab.add(b);
		int r[] = getTopK(ab, 3);
		for (int i = 0; i < r.length; i++) {
			System.out.print(r[i] + " ");
		}
	}

}
