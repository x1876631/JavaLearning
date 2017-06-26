package thinkingInJava.chapter18;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/**
 * Created by xuye on 2017-6-26
 * <p>
 * 格式化的内存输入
 */
public class FormattedMemoryInput {
	public static void main(String[] args) {
		DataInputStream in;
		try {
			in = new DataInputStream(
					new ByteArrayInputStream(
							BufferedInputFile
									.read("/Users/xuye/Documents/workspace/javaLearning/src/thinkingInJava/chapter18/FormattedMemoryInput.java")
									.getBytes()));
			// while (true) {
			// System.out.print((char) in.readByte());
			// }
			while (in.available() != 0) {
				System.out.print((char) in.readByte());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 由于in.readByte()读到的字符都是合法的，没法判断读取是否结束，只能看捕获异常来通知结束，可以用available查看还有多少可以读取的字符
			System.out.println("end of stream");
		}
	}
}
