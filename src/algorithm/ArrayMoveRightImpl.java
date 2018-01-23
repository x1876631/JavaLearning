package algorithm;

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

	public static void main(String[] args) {
		char[] array = new char[] { '1', '0', 'a', '0', 'c' };
		System.out.println("右移前的数组：" + Arrays.toString(array));
		// arrayMoveRightSimple(array, 2);
		arrayMoveRightBetter(array, 2);
		System.out.println("右移后的数组：" + Arrays.toString(array));
	}
}
