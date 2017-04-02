package thinkingInJava.chapter10;

import utils.PrintUtils;

/**
 * Created by xuye on 2017-4-2
 *
 * 序列类
 * 
 * 创建一个外部类对象不会自动创建其内部类，而创建一个内部类时一定要先创建外部类，除非是嵌套类(静态内容类)
 * 内部类被创建的时候，会默认持有一个指向其外部类对象的引用
 */
public class Sequence {

	private int s1 = PrintUtils.printInit("Sequence.s1 init", 1);
	private int s2;
	private Object[] items;// 序列内容
	private int next = 0;// 当前选中对象在序列里的下标

	public Sequence(int size) {
		items = new Object[size];
		s2 = PrintUtils.printInit("Sequence.s2 init", 2);
	}

	/**
	 * 向序列里添加新元素
	 * 
	 * @param x
	 *            新元素
	 */
	public void add(Object x) {
		if (next < items.length) {
			items[next] = x;
			next++;
		}
	}

	/**
	 * Created by xuye on 2017-4-2
	 * 
	 * private内部类，阻止了依赖类型的编码，并隐藏了实现的细节
	 *	
	 */
	private class SequenceSelector implements Selector {

		private int ss1 = PrintUtils.printInit("SequenceSelector.ss1 init", 1);
		public int ss2;

		public SequenceSelector() {
			ss2 = PrintUtils.printInit("SequenceSelector.ss2 init", 2);
		}

		private int i = 0;

		@Override
		public boolean end() {
			return i == items.length;
		}

		@Override
		public Object current() {
			return items[i];
		}

		@Override
		public void next() {
			if (i < items.length) {
				i++;
			}
		}

		/**
		 * @return 指向外部类对象引用
		 */
		public Sequence getSequence() {
			return Sequence.this;
		}
	}

	private int f() {
		System.out.println("Sequence.f() is called");
		SequenceSelector selector = new SequenceSelector();
		return selector.i;
	}
	
	/**
	 * 获取一个selector的实现
	 * 
	 * @return selector的实现：SequenceSelector
	 */
	public Selector selector() {
		return new SequenceSelector();
	}

	public static void main(String[] args) {
		Sequence sequence = new Sequence(10);
		for (int i = 0; i < 10; i++) {
			sequence.add(i);
		}
		// Selector selector = sequence.selector();
		SequenceSelector selector = sequence.new SequenceSelector();
		while (!selector.end()) {
			System.out.print(selector.current() + " ");
			selector.next();
		}
		System.out.println();
		selector.getSequence().f();
	}
}
