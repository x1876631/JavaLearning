package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtils {

	private static final int BYTE_SIZE = 4096;// 每次读取数据时允许的最大字节数

	public static void copyFile(String sourceFile, String targetFile)
			throws Exception {
		FileInputStream bis = new FileInputStream(sourceFile);
		FileOutputStream bos = new FileOutputStream(targetFile);
		byte[] data = new byte[BYTE_SIZE];// 创建一个缓冲字节数据，用于存储
		// bis.read从一个输入流里读取数据，写到一个字节数组里
		while (bis.read(data) > 0) {
			// 如果输入流能读到数据，并且写满了缓存，则返回写入的数据量，如果是-1，说明没有能读取的数据了，结束
			// 从缓冲里读取数据，写入输出流
			bos.write(data);
		}
		// 提交所有数据，关闭资源
		bis.close();
		bos.close();
		// System.out.println("----复制完成----，复制文件路径：" + targetFile);
	}

	// public static void main(String[] args) {
	// try {
	// copyFile(
	// "/Users/xuye/Documents/workspace/javaLearning/src/Utils/FileUtils.java",
	// "/Users/xuye/Documents/workspace/javaLearning/src/Utils/FileUtils.out");
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
}
