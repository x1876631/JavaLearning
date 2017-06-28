package thinkingInJava.chapter18;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by xuye on 2017-6-28
 * <p>
 * 使用nio复制文件
 */
public class ChannelCopy {
	private static final int BSIZE = 1024;
	private static final String SOURCE_FILE_NAME = "/Users/xuye/Documents/workspace/javaLearning/src/thinkingInJava/chapter18/ChannelCopy.java";
	private static final String OUTPUT_FILE_NAME = "/Users/xuye/Documents/workspace/javaLearning/src/thinkingInJava/chapter18/ChannelCopy.out";

	public static void main(String[] args) throws Exception {
		FileChannel in = new FileInputStream(SOURCE_FILE_NAME).getChannel();
		FileChannel out = new FileOutputStream(OUTPUT_FILE_NAME).getChannel();

		// ----第一种复制方法----
		ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
		// 每次从输入通道里读取最多1024字节(这个大小是buffer的大小)写入到buffer，然后再从buffer里读出来写入别的文件
		while (in.read(buffer) != -1) {
			// 切换buffer到读状态，让输出流可以读取buffer的数据
			buffer.flip();
			out.write(buffer);
			// 切换到写状态，让输入流可以继续写入数据
			buffer.clear();
		}
		System.out.println("----方法1，复制完成----");

		// ----更好的复制方式----
		// 直接使用通道对通道的方式，传输数据
		in.transferTo(0, in.size(), out);
		System.out.println("----更好的方式，复制完成----");
	}
}
