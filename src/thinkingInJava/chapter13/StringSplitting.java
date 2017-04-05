package thinkingInJava.chapter13;

import java.util.Arrays;

public class StringSplitting {
	private static String strings = "a~ B C-D...! nn~e ";

	/**
	 * 根据正则表达式规则，分组并打印
	 * 
	 * @param regex
	 *            正则表达式
	 */
	public static void mySplit(String regex) {
		System.out.println(Arrays.toString(strings.split(regex)));
	}

	public static void main(String[] args) {
		//string.split()：将字符串从正则表达式匹配的地方切开
		mySplit(" ");//按空格划分：[a~, B, C-D...!, nn~e]
		//有规则的划分，为什么规则内容都被删除了？
		mySplit("\\W+");//按非单词字符划分[a, B, C, D, nn, e]
		mySplit("n\\W+");//按字母n+非单词字符的规则划分[a~ B C-D...! n, e ]
		
		//字符串替换
		System.out.println(strings.replaceFirst("~", "[replace]"));//替换第一个匹配的字符串
		System.out.println(strings.replaceAll("~", "[replace]"));//替换所有匹配的字符串
	}
}
