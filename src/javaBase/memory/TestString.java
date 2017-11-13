package javaBase.memory;

/**
 * Created by xuye on 2017年11月13日
 * <p>
 * 学习字符串创建原理
 */
public class TestString {

	public static void main(String[] args) {
		// 情况1，直接使用常量声明String
		String s1 = "abc";// 直接使用常量声明，仅是在字符串常量池里创建了"abc"，然后让s1指向它
		System.out.println("s1 == abc: " + (s1 == "abc"));// 结果是true

		// 情况2，使用new的方式创建字符串
		String s2 = new String("abc");
		System.out.println("s2 == abc：" + (s2 == "abc"));// false
		System.out.println("s1 == s2：" + (s1 == s2));// false

		// 情况3，再用new创建一个abc字符串
		String s3 = new String("abc");
		System.out.println("s3 == s2：" + (s3 == s2));// false，因为s2和s3在堆里是2个对象，地址不同

		// 情况4，字符串常量拼接
		String s4 = "a" + "b" + "c";
		System.out.println("s4 == abc：" + (s4 == "abc"));// true，经过编译器优化，常量拼接和一个完整的常量一样，都在常量池里

		// 情况5，final变量和常量拼接
		final String s = "a";
		String s5 = s + "bc";
		// final修饰，相当于常量，变量和常量拼接变成了常量和常量拼接
		System.out.println("s5 == abc：" + (s5 == "abc"));// true

		// 情况6，普通变量合常量拼接
		String s61 = "a";
		String s62 = "bc";
		String s6 = s61 + s62;
		// 变量s61和s62拼接，由于String不可变，会调用StringBuilder生成一个新的字符串对象，
		// 在堆中存储，所以s6指向堆中新建的abc，不是常量池里的abc
		System.out.println("s6 == abc：" + (s6 == "abc"));// false

	}
}
