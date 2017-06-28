package thinkingInJava.chapter18;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by xuye on 2017-6-27
 * <p>
 * nio 通道的使用
 */
public class GetChannel {

	private static final int BSIZE = 1024;// 指定缓冲区大小
	private static final String OUTPUT_FILE_NAME = "/Users/xuye/Documents/workspace/javaLearning/src/thinkingInJava/chapter18/GetChannel.out";

	public static void main(String[] args) throws Exception {
		// 通过指定文件和输出流获取"通道"
		FileChannel fc = new FileOutputStream(OUTPUT_FILE_NAME).getChannel();
		// 通过wrap方法，创建ByteBuffer，通道fc读取bf的数据并写入自己
		fc.write(ByteBuffer.wrap("some text ".getBytes()));
		fc.close();

		// 打开一个随机读写流的通道
		fc = new RandomAccessFile(OUTPUT_FILE_NAME, "rw").getChannel();
		// 将通道指定到文件的最后位置，开始写入
		fc.position(fc.size());
		fc.write(ByteBuffer.wrap("some more".getBytes()));
		fc.close();

		fc = new FileInputStream(OUTPUT_FILE_NAME).getChannel();
		// 通过allocate方法指定缓冲区大小
		ByteBuffer buff = ByteBuffer.allocate(BSIZE);
		// 通道读取数据，写入缓冲区
		fc.read(buff);
		// 切换一下缓冲区的读写状态为读状态，原本写入时的position值给了limit，然后position置为0，表示从0到limit读取缓冲区的数据
		buff.flip();

		// 从缓冲区的position位置开始读数据，读到limit为止，并依次打印出来
		while (buff.hasRemaining()) {
			System.out.print((char) buff.get());
		}
	}
}
