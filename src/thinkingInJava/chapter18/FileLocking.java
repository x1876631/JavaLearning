package thinkingInJava.chapter18;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

public class FileLocking {

	private static final String FILE_NAME = "/Users/xuye/Documents/workspace/javaLearning/src/thinkingInJava/chapter18/FileLocking.java";

	public static void main(String[] args) throws Exception {
		FileOutputStream fos = new FileOutputStream(FILE_NAME);
		// 获得整个文件的FileLock，tryLock是非阻塞式的锁，如果获取不到返回null，而不是阻塞进程等待，直到获取到锁
		FileLock fl = fos.getChannel().tryLock();
		if (fl != null) {
			System.out.println("Locked File");
			TimeUnit.MILLISECONDS.sleep(100);
			fl.release();
			System.out.println("release lock");
		}
		fos.close();
	}
}
