package thinkingInJava.chapter11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by xuye on 2017-4-3
 *
 * 迭代器，将容器的遍历与容器底层的结构分离，统一了对容器的访问方式
 * 
 * iterator只能单向移动，ListIterator可以双向移动
 */
public class SimpleIterator {
	public static void main(String[] args) {
		List<String> strings = new ArrayList<String>(Arrays.asList("a", "b",
				"c"));
		Iterator<String> iterator = strings.iterator();
		while (iterator.hasNext()) {
			String string = iterator.next();
			System.out.println("current string : " + string);
		}
		ListIterator<String> listIterator = strings.listIterator(3);
		while (listIterator.hasPrevious()) {
			System.out.println("current :" + listIterator.previous()
					+ " ,previous index:" + listIterator.previousIndex()
					+ " , next index:" + listIterator.nextIndex());
			;
		}

	}
}
