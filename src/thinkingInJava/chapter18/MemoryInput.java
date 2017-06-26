package thinkingInJava.chapter18;

import java.io.StringReader;

/**
 * Created by xuye on 2017-6-26
 * <p>
 * 从内存中读取数据
 */
public class MemoryInput {
	public static void main(String[] args) throws Exception {
		StringReader in = new StringReader(
				BufferedInputFile
						.read("/Users/xuye/Documents/workspace/javaLearning/src/thinkingInJava/chapter18/MemoryInput.java"));
		int c;
		while ((c = in.read()) != -1) {
			System.out.print((char) c);
		}
	}
}
