package thinkingInJava.chapter16;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by xuye on 2017-4-11
 *
 * 实现Comparable接口的类，具有了比较的功能
 */
public class CompType implements Comparable<CompType> {

	int i, j;
	private static int count = 1;

	public CompType(int n1, int n2) {
		i = n1;
		j = n2;
	}

	@Override
	public String toString() {
		String result = "[i = " + i + " , j = " + j + "]";
		if (count++ % 3 == 0) {
			// 如果创建的对象数是3的倍数了，加个换行，方便观看测试结果
			result += "\n";
		}
		return result;
	}

	/*
	 * 如果当前对象小于传入的参数，则返回负值；如果相等返回零值；如果大于，返回正值。 这里自定义比较了CompType的i值
	 */
	@Override
	public int compareTo(CompType compType) {
		return i < compType.i ? -1 : (i == compType.i ? 0 : 1);
	}

	public static void main(String[] args) {
		CompType[] compTypes = { new CompType(78, 55), new CompType(50, 50),
				new CompType(51, 89) };
		System.out.println("before sorting:");
		System.out.println(Arrays.toString(compTypes));
		// 如果数组元素没有实现Comparable，sort时会报异常，因为会将数组元素装换成Comparable
		Arrays.sort(compTypes);
		System.out.println("after sorting:");
		System.out.println(Arrays.toString(compTypes)+"\n");
		
		System.out.println("before reverse sorting:");
		System.out.println(Arrays.toString(compTypes));
		//sort接受一个要排序数组和比较规则，这里使用反序
		Arrays.sort(compTypes,Collections.reverseOrder());
		System.out.println("after reverse sorting:");
		System.out.println(Arrays.toString(compTypes));
	}

}
