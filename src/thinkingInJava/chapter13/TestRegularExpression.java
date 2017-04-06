package thinkingInJava.chapter13;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegularExpression {

	/**
	 * 在src目录下执行(已经javac编译过该类了) java
	 * thinkingInJava.chapter13.TestRegularExpression "abcabcabcdefabc" "abc+"
	 * "(abc)+" "(abc){2,}"
	 * 
	 * @param args
	 *            命令行 输入的参数数组 本例中参数为：abcabcabcdefabc "abc+" "(abc)+" "(abc){2.}"
	 */
	public static void main(String[] args) {
		//匹配一个或者多个单词字符
		Matcher matcher = Pattern.compile("\\w+").matcher(
				"Evening is");
		while (matcher.find()) {
			//matcher.group()返回前一次操作的的第0组(整个匹配)
			System.out.print(matcher.group() + " ");
		}
		System.out.println();
		int i = 0;
		while (matcher.find(i)) {
			System.out.print("i=" + i + ", " + matcher.group() + " ");
			i++;
		}
		System.out.println();
		// 如果输入参数小于2个就退出程序，因为最少需要2个参数，一个参数表示要匹配的字符串，一个参数表示匹配规则
		if (args.length < 2) {
			System.out.println("Usage:\njava TestRegularExpression "
					+ "characterSequence regularExpression+");
			System.exit(0);
		}
		System.out.println("input : " + args[0] + "\n");
		for (String arg : args) {
			System.out.println("Regular expression : " + arg);
			// compile()传入正则表达式，再调用matcher()方法传入要匹配的对象，生成Matcher对象，最后调用Matcher对象的各种方法匹配
			Pattern pattern = Pattern.compile(arg);
			Matcher matcher2 = pattern.matcher(args[0]);
			// matchre.find()表示查找多个匹配
			while (matcher.find()) {
				System.out.println("Match " + matcher2.group()
						+ " at positions " + matcher2.start() + "-"
						+ (matcher2.end() - 1));
			}
		}
		/**
		 * 输出结果 input : abcabcabcdefabc
		 * 
		 * Regular expression : abcabcabcdefabc
		 * 
		 * Match abcabcabcdefabc at positions 0-14
		 * 
		 * Regular expression : abc+ (表示ab+多个c)
		 * 
		 * Match abc at positions 0-2
		 * 
		 * Match abc at positions 3-5
		 * 
		 * Match abc at positions 6-8
		 * 
		 * Match abc at positions 12-14
		 * 
		 * Regular expression : (abc)+ (表示多个abc组合)
		 * 
		 * Match abcabcabc at positions 0-8
		 * 
		 * Match abc at positions 12-14
		 * 
		 * Regular expression : (abc){2,} (表示至少2组abc)
		 * 
		 * Match abcabcabc at positions 0-8
		 */

	}
}
