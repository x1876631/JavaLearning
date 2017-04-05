package thinkingInJava.chapter13;

import java.util.ArrayList;
import java.util.List;

public class InfiniteRecursion {
	@Override
	public String toString() {
		// 我们想打印该对象的内存地址，使用了this，但是由于自动转型导致了异常
		// return "InfiniteRecursion address : " + this+"\n";
		// 应该用Object的toString()方法
		return "InfiniteRecursion address : " + super.toString() + "\n";
	}

	public static void main(String[] args) {
		List<InfiniteRecursion> list = new ArrayList<InfiniteRecursion>();
		for (int i = 0; i < 10; i++) {
			list.add(new InfiniteRecursion());
		}
		/**
		 *
		 * 调用list的toString()时会依次调用列表元素的toString()，
		 * 而列表元素InfiniteRecursion类的toString里，由于使用string+xx的方式，xx被自动转型为string，
		 * 然后转换的过程中，又调用xx自己的toString(),
		 * 导致了递归调用，最终报错：java.lang.StackOverflowError，栈深度溢出。
		 */
		 System.out.println(list);
	}
}
