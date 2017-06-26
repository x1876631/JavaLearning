package thinkingInJava.chapter18;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * Created by xuye on 2017-6-26
 * <p>
 * 基本的文件输出
 */
public class BasicFileOutput {
	private static String outFileName = "/Users/xuye/Documents/workspace/javaLearning/src/thinkingInJava/chapter18/BasicFileOutput.out";
	private static String readFileName = "/Users/xuye/Documents/workspace/javaLearning/src/thinkingInJava/chapter18/BasicFileOutput.java";

	public static void main(String[] args) throws Exception {
		// 读取readFileName文件内容
		BufferedReader in = new BufferedReader(new StringReader(
				BufferedInputFile.read(readFileName)));
		// 指定写入文件为outFileName
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				outFileName)));
		int lineCount = 1;
		String s;
		while ((s = in.readLine()) != null) {
			// 逐行读取文件内容，如果有数据，就加个行号并写入
			out.println(lineCount++ + ": " + s);
		}
		out.close();
		// 打印写入文件的内容
		System.out.println("----输出文件的内容如下：----");
		System.out.println(BufferedInputFile.read(outFileName));
	}
}
