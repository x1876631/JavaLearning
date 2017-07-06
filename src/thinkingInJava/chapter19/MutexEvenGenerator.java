package thinkingInJava.chapter19;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xuye on 2017年7月6日
 * <p>
 * 使用Lock类保证同步
 */
public class MutexEvenGenerator extends IntGenerator {

	private int currentEventValue = 0;
	private Lock lock = new ReentrantLock();

	@Override
	public int next() {
		//
		lock.lock();
		try {
			++currentEventValue;
			Thread.yield();
			++currentEventValue;
			return currentEventValue;
		} finally {
			lock.unlock();
		}
	}

}
