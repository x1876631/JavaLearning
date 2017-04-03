package thinkingInJava.chapter11;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SetTest {
	public static void main(String[] args) {
		Random random = new Random(47);
		Set<Integer> intset = new HashSet<Integer>();
		//生成了10000个元素，但是set里只有29个元素，重复的值都添加失败了
		for (int i = 0; i < 10000; i++) {
			intset.add(random.nextInt(30));
		}
		System.out.println(intset);

		Set<String> stringSet = new HashSet<String>();
		Collections.addAll(stringSet, "a b c d e".split(" "));
		Set<String> stringSet2 = new HashSet<String>();
		stringSet2.addAll(Arrays.asList("a","f"));
		System.out.println(stringSet.containsAll(stringSet2));
		//removeAll()会把共有的元素移除
		System.out.println("1 remove 2 ? " + stringSet.removeAll(stringSet2));
		System.out.println(stringSet);
		
	}
}
