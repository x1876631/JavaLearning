package thinkingInJava.chapter18;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by xuye on 2017-6-28
 * <p>
 * IO流与映射文件访问的性能差距
 */
public class MappedIO {
	private static int numOfInts = 400 * 0000;
	private static int numOfUnbuffInts = 20 * 0000;

	private static final String OUTPUT_FILE_NAME = "/Users/xuye/Documents/workspace/javaLearning/src/thinkingInJava/chapter18/MappedIO.out";

	private abstract static class Tester {
		private String name;

		public Tester(String name) {
			this.name = name;
		}

		public void runTest() {
			System.out.print(name + ": ");
			long start = System.nanoTime();// 记录读写的开始时间
			try {
				test();// 执行读写操作
				double duration = System.nanoTime() - start;// 记录读写操作耗时
				System.out.format("%.5f\n", duration / 1.0e9);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public abstract void test() throws Exception;
	}

	private static Tester[] tests = { new Tester("Stream Write") {

		@Override
		public void test() throws Exception {
			DataOutputStream dos = new DataOutputStream(
					new BufferedOutputStream(new FileOutputStream(
							OUTPUT_FILE_NAME)));
			// 使用数据流去写入文件
			for (int i = 0; i < numOfInts; i++) {
				dos.writeInt(i);
			}
			dos.close();
		}
	}, new Tester("Mapped write") {

		@Override
		public void test() throws Exception {
			FileChannel fc = new RandomAccessFile(OUTPUT_FILE_NAME, "rw")
					.getChannel();
			// 使用内存映射文件的方式拿到文件对于的缓冲器
			IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size())
					.asIntBuffer();
			// 向缓冲器里写入数据
			for (int i = 0; i < numOfInts; i++) {
				ib.put(i);
			}
			fc.close();
		}
	}, new Tester("Stream Read") {

		@Override
		public void test() throws Exception {
			DataInputStream dis = new DataInputStream(new BufferedInputStream(
					new FileInputStream(OUTPUT_FILE_NAME)));
			for (int i = 0; i < numOfInts; i++) {
				dis.readInt();
			}
			dis.close();
		}
	}, new Tester("Mapped Read") {

		@Override
		public void test() throws Exception {
			FileChannel fc = new FileInputStream(OUTPUT_FILE_NAME).getChannel();
			IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size())
					.asIntBuffer();
			while (ib.hasRemaining()) {
				ib.get();
			}
			fc.close();
		}
	}

	};

	public static void main(String[] args) {
		for (Tester tester : tests) {
			tester.runTest();
		}
	}
}
