package algorithm;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by xuye on 2018年1月23日
 * <p>
 * 数组元素为n，循环右移k位，要求空间复杂度尽量小，时间复杂度不限<br/>
 * 参考：http://blog.csdn.net/qq_34594236/article/details/51707464<br/>
 */
public class ArrayMoveRightImpl {

	/**
	 * 最简单的算法(我自己想的)，把要位移的k位取出来，然后放入原来的数组。<br/>
	 * 空间复杂度：需要一个额外的数组
	 * 
	 * @param array
	 *            待右移的数组
	 * @param k
	 *            右移的位数
	 */
	public static char[] arrayMoveRightSimple(char[] array, int k) {
		if (needReturn(array, k)) {
			return array;
		} else {
			// 额外的数组，承载要右移的k个数
			char[] kArray = new char[k];
			int n = array.length;
			for (int i = 0; i < k; i++) {
				// 把原数组的k个数都取出来，给kArray
				kArray[i] = array[n - k + i];
			}

			// 将其他的n-k个元素，全都右移k位
			for (int j = n - k - 1; j >= 0; j--) {
				array[j + k] = array[j];
			}

			// 再把原本取出来的k个数据放回进原数组
			for (int m = 0; m < k; m++) {
				array[m] = kArray[m];
			}
			return array;
		}
	}

	/**
	 * 更好的办法，在当前数组内，反转分成2部分的元素，再对整个数组反转。这种方法叫三次反转法。。。<br/>
	 * 空间复杂度：不需要额外的空间
	 * 
	 * @param array
	 *            待右移的数组
	 * @param k
	 *            右移的位数
	 */
	public static char[] arrayMoveRightBetter(char[] array, int k) {
		if (needReturn(array, k)) {
			return array;
		} else {
			int n = array.length;
			reverse(array, n - k, n - 1);
			reverse(array, 0, n - k - 1);
			reverse(array, 0, n - 1);
			return array;
		}
	}

	/**
	 * 反转数组部分内容
	 * 
	 * @param array
	 *            数组
	 * @param beginIndex
	 *            反转范围起始index
	 * @param endIndex
	 *            反转范围结束index
	 */
	public static void reverse(char[] array, int beginIndex, int endIndex) {
		// 交换次数是范围数的一半，交换方法时用交换范围前头的值换范围后面的值，换到中间那个数为值
		for (int i = 0; i < (endIndex - beginIndex + 1) / 2; i++) {
			char temp = array[beginIndex + i];// 拿到范围前部分的值
			array[beginIndex + i] = array[endIndex - i];// 把范围后部分的值交换给前面
			array[endIndex - i] = temp;// 把范围前部分的值给范围后部分
		}
	}

	public static boolean needReturn(char[] array, int k) {
		if (array == null || array.length == 0 || k <= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 从数组中取出指定值，将所有指定值都提到数组前面，其他的非指定值保持顺序后移，要求空间复杂度尽量小<br/>
	 * 例子：[a0b0c]变成[00abc]<br/>
	 * 
	 * @param array
	 *            待操作的数组
	 * @param k
	 *            待移动的指定值
	 * @return 移动后的数组
	 */
	public static char[] moveValueToFrontSimple(char[] array, char value) {
		// 简单的思路就是从数组中取出所有指定值，再取出所有非指定值，然后在指定值数组后添加非指定值
		ArrayList<Character> valueList = new ArrayList<Character>();// 存放指定value
		ArrayList<Character> otherList = new ArrayList<Character>();// 存放其他value
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				valueList.add(array[i]);
			} else {
				otherList.add(array[i]);
			}
		}
		valueList.addAll(otherList);
		for (int j = 0; j < array.length; j++) {
			array[j] = valueList.get(j);
		}
		return array;
	}

	/**
	 * 这种方法不需要额外数组，只需要个额外变量保存待交换的值，空间复杂度应该是(1)，时间复杂度没算出来。。。
	 */
	public static char[] moveValueToFrontBetter(char[] array, char value) {
		// 从头开始遍历，如果不是指定值，则在其后面范围找指定值，找到交换，并将原本的值插入到查找范围里，保证原顺序
		for (int i = 0; i < array.length; i++) {
			if (array[i] != value) {
				System.out.println("发生了交换查找：i=" + i + ", array[i]=" + array[i]);
				for (int j = i + 1; j < array.length - 1; j++) {
					if (array[j] == value) {
						System.out.println("交换查找后遍历：j=" + j + ", array[j]="
								+ array[j]);
						char temp = array[i];// 保存一下要交换的非指定值

						// 后移查找范围所有值，并把非指定值插入到查找范围的首位
						int beginIndex = i + 1;
						int endIndex = j;
						insertValueInStart(array, beginIndex, endIndex, temp);

						// 将指定值赋值给待交换的位置
						array[i] = value;
						System.out.println("插入并交换后，数组值为："
								+ Arrays.toString(array));
						break;
					}
				}

				// 优化时间复杂度
				if (array[i] != value) {
					System.out.println("本次交换查找没有找到指定值，后续无需再遍历，程序结束");
					// 遍历结束后，也没找到指定值(即没发生过交换，array[i]不是value)
					return array;
				}
			}
		}
		return array;
	}

	/**
	 * 将value插入指定范围最前面，然后将范围内的值全后移1位
	 */
	public static void insertValueInStart(char[] array, int beginIndex,
			int endIndex, char insertValue) {
		for (int i = endIndex; i > beginIndex; i--) {
			// 右移所有元素，最后1位被挤掉
			array[i] = array[i - 1];
		}
		// 将要插入的值，赋值给begin，完成插入
		array[beginIndex] = insertValue;
	}

	public static void main(String[] args) {
		char[] array = new char[] { 'a', 'b', '0', '0', 'c' };
		System.out.println("右移前的数组：" + Arrays.toString(array));
		// arrayMoveRightSimple(array, 2);
		arrayMoveRightBetter(array, 2);
		System.out.println("右移后的数组：" + Arrays.toString(array));

		array = new char[] { 'a', 'b', '0', '0', 'c' };
		System.out.println("\n----元素提取前的数组：" + Arrays.toString(array) + "\n");
		// moveValueToFrontSimple(array, '0');
		moveValueToFrontBetter(array, '0');
		System.out.println("\n----元素提取后的数组：" + Arrays.toString(array));
	}
}
