package thinkingInJava.chapter11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AddingGroups {
	public static void main(String[] args) {
		// 容器支持传入一组容器初始化，但不推荐这种方法，用addAll更快
		Collection<Integer> collection = new ArrayList<Integer>(Arrays.asList(
				1, 2, 3, 4, 5));

		// 使用addAll()方法添加一个容器
		Integer[] moreInts = { 6, 7, 8, 9, 10 };
		collection.addAll(Arrays.asList(moreInts));
		Collections.addAll(collection, 11, 12, 13, 14, 15);
		Collections.addAll(collection, moreInts);
		System.out.println(collection.toString());

		// Array.asList生成的列表不能add或者delete，因为这个列表是Arrays自己实现的，底层是数组，不支持改变容量大小
		// Arrays.asList(1,2,3).add(4);//会报错

		AddingGroups groups = new AddingGroups();
		List<Snow> snow1 = Arrays.asList(groups.new Powder(),
				groups.new Crusty(), groups.new Slush());
		
		List<Snow> snow2 = Arrays.asList(groups.new Light(),groups.new Heavy());
		List<Snow> snow3 = Arrays.<Snow>asList(groups.new Light(),groups.new Heavy());
	}

	class Snow {
	}

	class Powder extends Snow {
	}

	class Crusty extends Snow {
	}

	class Slush extends Snow {
	}

	class Light extends Powder {
	}

	class Heavy extends Powder {
	}
}
