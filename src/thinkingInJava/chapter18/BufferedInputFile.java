package thinkingInJava.chapter18;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by xuye on 2017-6-25
 * <p>
 * 缓冲读取文件
 */
public class BufferedInputFile {
	public static String read(String filename) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String s;// 承载文件一行内容的字符串
		StringBuilder sb = new StringBuilder();
		// 逐行读取文件内容
		while ((s = in.readLine()) != null) {
			sb.append(s + "\n");
		}
		in.close();// 读取完毕后关闭输入流
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		System.out
				.println(read("/Users/xuye/Documents/workspace/javaLearning/src/thinkingInJava/chapter18/BufferedInputFile.java"));
	}
}
