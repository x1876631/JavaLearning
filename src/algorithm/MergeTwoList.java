package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by xuye on 2018年1月13日
 * <p>
 * 两个有序链表，有重复元素，合并出有序无重复元素链表<br/>
 * 参考：http://www.tk4479.net/u013159040/article/details/45043349
 */
public class MergeTwoList {
	public static ArrayList<String> merge(ArrayList<String> array1,
			ArrayList<String> array2) {
		for (int i = 0; i < array2.size(); i++) {
			if (array1.contains(array2.get(i))) {
				array2.remove(i);
			}
		}
		array1.addAll(array2);
		array1.sort(null);
		return array1;
	}

	public static LinkedList<String> merge(LinkedList<String> array1,
			LinkedList<String> array2) {
		for (int i = 0; i < array2.size(); i++) {
			if (array1.contains(array2.get(i))) {
				array2.remove(i);
			}
		}
		array1.addAll(array2);
		array1.sort(null);
		return array1;
	}

	public static void main(String[] args) {
		testMergeLinkedList();
	}

	public static void testMergeArray() {
		ArrayList<String> array1 = new ArrayList<String>();
		ArrayList<String> array2 = new ArrayList<String>();
		array1.add("b");
		array1.add("c");
		array1.add("d");
		array1.add("e");

		array2.add("a");
		array2.add("c");
		array2.add("f");
		ArrayList<String> result = merge(array1, array2);

		System.out.println(result.toString());
	}

	public static void testMergeLinkedList() {
		LinkedList<String> array1 = new LinkedList<String>();
		LinkedList<String> array2 = new LinkedList<String>();
		array1.add("b");
		array1.add("c");
		array1.add("d");
		array1.add("e");

		array2.add("a");
		array2.add("c");
		array2.add("f");
		LinkedList<String> result = merge(array1, array2);

		System.out.println(result.toString());
	}

}
