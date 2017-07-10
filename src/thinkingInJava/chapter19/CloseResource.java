package thinkingInJava.chapter19;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuye on 2017年7月10日
 * <p>
 * 通关关闭资源中断阻塞
 */
public class CloseResource {
	public static void main(String[] args) throws Exception {
		ExecutorService ex = Executors.newCachedThreadPool();
		ServerSocket server = new ServerSocket(8080);
		InputStream socketInput = new Socket("localhost", 8080)
				.getInputStream();
		// 新建2个线程，因为等待读取数据而阻塞
		ex.execute(new IOBlocked(socketInput));
		ex.execute(new IOBlocked(System.in));

		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("shutting down all threads");
		ex.shutdownNow();

		TimeUnit.SECONDS.sleep(1);
		System.out.println("closing " + socketInput.getClass().getSimpleName());
		// 关闭网络输入流，一关闭IO就结束了阻塞
		socketInput.close();

		TimeUnit.SECONDS.sleep(1);
		System.out.println("closing " + System.in.getClass().getSimpleName());
		System.in.close();
	}
}
