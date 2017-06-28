package thinkingInJava.chapter18;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by xuye on 2017-6-28
 * <p>
 * 内存映射文件
 * <P>
 * 内容映射文件，就是将文件部分映射到java堆以外的内存中，让java程序直接从内存中访问这些映射后的文件
 * 优点是：1、可以借此创建和修改那些无法放入内存中的超大文件 2、读写速度非常快，比nio还快
 */
public class LargeMappedFiles {
	static int length = 0x8FFFFFF;// 128M的数据
	private static final String OUTPUT_FILE_NAME = "/Users/xuye/Documents/workspace/javaLearning/src/thinkingInJava/chapter18/LargeMappedFiles.out";

	public static void main(String[] args) throws Exception {
		// 指定一个输出文件
		MappedByteBuffer out = new RandomAccessFile(OUTPUT_FILE_NAME, "rw")
				.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);
		// 循环写入一个字符，一共写入了
		for (int i = 0; i < length; i++) {
			out.put((byte) 'x');
		}
		System.out.println("finished writing");
		for (int i = length / 2; i < length / 2 + 6; i++) {
			System.out.print((char) out.get(i) + " ");
		}
	}
}
