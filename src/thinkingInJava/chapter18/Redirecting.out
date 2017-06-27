package thinkingInJava.chapter18;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Created by xuye on 2017-6-27
 * <p>
 * 标准io重定向
 */
public class Redirecting {

	private static final String INPUT_FILE_NAME = "/Users/xuye/Documents/workspace/javaLearning/src/thinkingInJava/chapter18/Redirecting.java";
	private static final String OUTPUT_FILE_NAME = "/Users/xuye/Documents/workspace/javaLearning/src/thinkingInJava/chapter18/Redirecting.out";

	public static void main(String[] args) throws Exception {
		// 先保存一下标准输出，最后要恢复一下
		PrintStream console = System.out;

		// 指定数据源为INPUT_FILE_NAME文件
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				new File(INPUT_FILE_NAME)));
		// 标准输入重定向到in
		System.setIn(in);

		// 指定输出源为OUTPUT_FILE_NAME文件
		PrintStream out = new PrintStream(new BufferedOutputStream(
				new FileOutputStream(OUTPUT_FILE_NAME)));
		// 标准输出和标准错误都重定向到out
		System.setOut(out);
		System.setErr(out);

		// 读取标准输入(这里标准输入改为从文件读取，而不是控制台)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s;
		// 将标准输入读取到的数据输出到标准输出(这里标准输出改为了另一个文件，所以其实就是读取input文件到output文件)
		while ((s = br.readLine()) != null) {
			System.out.println(s);
		}
		out.close();

		// 恢复标准的输出
		System.setOut(console);
		System.out.println("io重定向测试结束!");
	}
}
