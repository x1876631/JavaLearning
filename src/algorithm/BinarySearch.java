package algorithm;

/**
 * Created by xuye on 2017年12月7日
 * <p>
 * 二分查找：在一个有序数组里找指定的一个数<br/>
 * 实现原理：<br/>
 * 拿指定的数和数组中间的数比较，如果比中间数小，就在前半段数组里继续找，否则则在后半段数组里找，<br/>
 * 如果中间数正好是指定的数就返回数组下标，如果找不到返回-1
 * 
 * 【时间复杂度】O(logn)，因为最坏的情况是n个数，经过2^k次查找后，剩余1个数。 即 n/(2^k) = 1，则k=log2(n)<br/>
 * 参考：http://blog.csdn.net/u010983881/article/details/51074336
 * 
 * 【遇到的问题】<br/>
 * 1、每次递归执行完，完要返回一个查找结果，否则即使查找到了正确结果，也会继续执行后续判断逻辑查找下去<br/>
 * 2、mid是整个数组的下标，记得加上begin<br/>
 * 3、当只剩下2个数时，对折取整会导致mid差值 = 0，一直递归查2个数中的那个begin(在后半段)或者end(在前半段)，导致栈溢出，<br/>
 * 传入范围时，应该让begin=mid+1和end=mid-1，而不能直接传mid，这样就避免了重复查询那个边界值导致的递归溢出<br/>
 * 4、循环查找的条件是while(begin<=end)，要<=
 */
public class BinarySearch {

	public static void main(String[] args) {

		// changeDoubleToInt(2.7);
		// changeDoubleToInt(2.3);
		// changeDoubleToInt(-2.3);

		int need = 44;

		int[] list = { 11, 22, 33, 44, 55, 66, 77, 88 };
		System.out.println(BinarySearch.searchRecursive(list, 0,
				list.length - 1, need));

		System.out.println("\n----对比递归和循环----\n");

		System.out.println(BinarySearch.searchLoop(list, 0, list.length - 1,
				need));

	}

	/**
	 * 递归查询
	 * 
	 * @param list
	 *            查询数组
	 * @param begin
	 *            本次查询范围的起始数组下标
	 * @param end
	 *            本次查询范围的末尾数组下标
	 * @param need
	 *            指定要查找的数
	 * @return 查找数在数组里的下标，如果找不到返回-1
	 */
	public static int searchRecursive(int[] list, int begin, int end, int need) {
		if (list == null || list.length == 0 || need > list[end]
				|| need < list[begin]) {
			return -1;
		}

		int mid = (end - begin) / 2 + begin;
		System.out.println("本次查找数组size：" + list.length + ", 中间数下标：" + mid);
		// 先检查下指定的数是否和数组中间数相等
		if (list[mid] == need) {
			// System.out.println("找到了本次要查的数：" + need + "，该数值在数组中的下标为：" + mid);
			return mid;
		} else if (list[mid] > need) {
			// 指定的数在后半段
			end = mid - 1;// 注意这里end要用mid-1，不然只有2个数时，会一直循环查询end，递归溢出，下面的begin同理
			return searchRecursive(list, begin, end, need);
		} else if (list[mid] < need) {
			// 指定的数在前半段
			begin = mid + 1;
			return searchRecursive(list, begin, end, need);
		}
		// System.out.println("未找到要找的值：" + mid + "，本次查询返回值：-1");
		return -1;
	}

	/**
	 * 循环查询
	 * 
	 * @param list
	 * @param begin
	 * @param end
	 * @param need
	 * @return
	 */
	public static int searchLoop(int[] list, int begin, int end, int need) {
		if (list == null || list.length == 0 || need > list[end]
				|| need < list[begin]) {
			return -1;
		}

		int mid = 0;
		// 注意判断条件要<=，否则如果begin=end就是要找的值时，反而会返回-1
		while (begin <= end) {
			mid = (end - begin) / 2 + begin;
			System.out.println("本次查找数组size：" + list.length + ", 中间数下标：" + mid);
			if (list[mid] == need) {
				return mid;
			} else if (list[mid] > need) {
				end = mid - 1;
			} else if (list[mid] < need) {
				begin = mid + 1;
			}
		}
		return -1;
	}

	/**
	 * 测试强制类型转换的取整逻辑：直接干掉小数位，而不是四舍五入
	 * 
	 * @param testValue
	 *            待强转的double类型
	 */
	public static void changeDoubleToInt(double testValue) {
		System.out.println("int value: " + (int) testValue);
	}
}
