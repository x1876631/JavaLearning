package thinkingInJava.chapter16;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by xuye on 2017-4-11
 * 
 * 数组方法学习
 */
public class ArrayTest {

	public static void main(String[] args) {
		int[] i = new int[7];
		int[] j = new int[10];
		Arrays.fill(i, 7);
		Arrays.fill(j, 10);
		System.arraycopy(i, 0, j, 0, i.length);
		System.out.println(Arrays.toString(j));
		// 复制i到j，结果是[7, 7, 7, 7, 7, 7, 7, 10, 10, 10]

		try {
			// 下面的代码会报异常，java.lang.ArrayStoreException，将错误的类型赋给数组元素
			// 因为System.arraycopy不会自动拆装箱，所以int和Integer不一样
			Integer[] i1 = new Integer[7];
			int[] j1 = new int[10];
			Arrays.fill(i1, 7);
			Arrays.fill(j1, 10);
			System.arraycopy(i1, 0, j1, 0, i.length);
			System.out.println(Arrays.toString(j1));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 比较数组
		String[] s1 = new String[4];
		Arrays.fill(s1, "s");
		String[] s2 = { new String("s"), new String("s"), new String("s"),
				new String("s") };
		System.out.println("s1[] equals s2[]: " + Arrays.equals(s1, s2));

		// 对排序数组查找
		int[] searchArray = { 128, 998, 207, 809, 288, 555, 861, 140, 258, 288 };
		Arrays.sort(searchArray);
		System.out.println("sort array: "+Arrays.toString(searchArray));
		//1、二分查找如果找到了目标，则返回>=0的值，如果找不到，则返回负值，表示应该插入的位置
		//2、如果排序时，使用了自定义排序规则，则查找时也得使用相同规则
		int location = Arrays.binarySearch(searchArray, 555);
		System.out.println("search one's location in Array :"+location);
	}
}
