package thinkingInJava.chapter18;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by xuye on 2017-6-26
 * <p>
 * 读取二进制文件
 */
public class BinaryFile {

	private static final String filename = "/Users/xuye/Documents/workspace/javaLearning/src/thinkingInJava/chapter18/BinaryFile.java";

	public static byte[] read(File file) {
		BufferedInputStream bf = null;
		byte[] data = null;
		try {
			// 执行读取文件为file
			bf = new BufferedInputStream(new FileInputStream(file));
			// 在内存里创建一个file字节数大小的字节数组，用来装file的内容
			data = new byte[bf.available()];
			// 使用输入流读取file到字节数组中
			bf.read(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (bf != null) {
					// 用完输入流，都要关闭一下
					bf.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 返回读取的字节数据
		return data;
	}

	/**
	 * read的重载方法，支持只传入文件名
	 * 
	 * @param filename
	 * @return
	 */
	public static byte[] read(String filename) {
		return read(new File(filename).getAbsoluteFile());
	}

	public static void main(String[] args) {
		byte[] bytes = read(filename);
		for (byte b : bytes) {
			System.out.print((char) b);
		}
	}
}
