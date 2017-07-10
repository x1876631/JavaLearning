package thinkingInJava.chapter19;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuye on 2017年7月10日
 * <p>
 * 使用NIO灵活处理不同类型的中断
 */
public class NIOInterruption {
	public static void main(String[] args) throws Exception {
		ExecutorService ex = Executors.newCachedThreadPool();
		ServerSocket socket = new ServerSocket(8080);
		InetSocketAddress isa = new InetSocketAddress("localhost", 8080);
		SocketChannel sc1 = SocketChannel.open(isa);
		SocketChannel sc2 = SocketChannel.open(isa);
		// 执行通道1的读取任务
		Future<?> f = ex.submit(new NIOBlocked(sc1));
		// 执行通道2读取任务
		ex.execute(new NIOBlocked(sc2));
		// 停止接受新任务
		ex.shutdown();

		// 使用shutdownNow更加方便且正统，而不需要关闭资源
		// TimeUnit.SECONDS.sleep(1);
		// ex.shutdownNow();

		TimeUnit.SECONDS.sleep(1);
		// 主动中断任务1
		f.cancel(true);

		TimeUnit.SECONDS.sleep(1);
		// 通过关闭通道中断任务2
		sc2.close();
	}
}

/**
 * Created by xuye on 2017年7月10日
 * <p>
 * 使用NIO实现更灵活的中断响应
 */
class NIOBlocked implements Runnable {
	private final SocketChannel sc;

	public NIOBlocked(SocketChannel sc) {
		this.sc = sc;
	}

	@Override
	public void run() {
		try {
			System.out.println("waiting for read() in "
					+ this.getClass().getSimpleName());
			sc.read(ByteBuffer.allocate(1));
		} catch (ClosedByInterruptException e) {
			// 当操作这个channel的线程被中断时，关闭该channel，被中断的线程抛出该异常
			System.out.println("ClosedByInterruptException");
		} catch (AsynchronousCloseException e) {
			// 当线程在I/O操作中关闭通道或阻塞通道的部分时，由线程检查异常，即该异常由通道关闭引起
			System.out.println("AsynchronousCloseException");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Exiting NIOBlocked.run()");
	}

}