package thinkingInJava.chapter16;

import java.util.Arrays;

/**
 * Created by xuye on 2017-4-11
 *	
 * 多维数组学习
 */
public class MultiArray {
	public static void main(String[] args) {
		int[][][] ints = new int[2][3][4];
		// deepToArray将多维数组转换为字符串，该方法只能操作多维数组
		System.out.println(Arrays.deepToString(ints));
		// Arrays.fill(ints, 1);//这个方法只能操作一维数组

		int[] intArray = new int[5];
		Arrays.fill(intArray, 1, 3, 777);//从intArray[1] ~ intArray[3-1]填充777
		System.out.println(Arrays.toString(intArray));
	}
}
