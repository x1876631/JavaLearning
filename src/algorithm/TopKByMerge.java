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
	 *            n个有序的待合并数组
	 * @param k
	 *            要求得的合并后的元素个数
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

			// 遍历各个数组，挨个从每个排好序的数组里取还没扫描过的值，即list.get(index[j])
			for (int j = 0; j < input.size(); j++) {

				List<Integer> list = input.get(j);

				// 如果第j数组已经取完了，则跳过该数组
				if (index[j] < list.size()) {
					// 第j数组还有值可以取，则取出来和本次的max比较，如果比max大，就更新max
					if (max < list.get(index[j])) {
						max = list.get(index[j]);

						// 记录一下max元素产生于哪个数组，下次取值从这个数组的下一位开始取
						maxIndex = j;
					}
				}
			}
			if (max == Integer.MIN_VALUE) {
				// 如果都是空数组，返回空结果
				return result;
			}

			// 本次比较，得到了一个max值，保存到结果数组里
			result[i] = max;

			// 记录一下本次的最大值是哪个数组里的，下次从这个值的下一个值开始取值
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
