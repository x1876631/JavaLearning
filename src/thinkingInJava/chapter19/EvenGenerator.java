package thinkingInJava.chapter19;

/**
 * Created by xuye on 2017年7月6日
 * <p>
 * 偶数生成器
 */
public class EvenGenerator extends IntGenerator {

	private int currentEvenValue = 0;

	@Override
	public int next() {
		// 自增多次，危险，一个任务可能在另一个任务自增1次后，调用next()
		// 另外自增操作也不是原子的，递增过程中任务可能会被线程机制挂起
		++currentEvenValue;
		++currentEvenValue;
		return currentEvenValue;
	}

	public static void main(String[] args) {
		// EvenChecker.test(new EvenGenerator());//未同步的多线程读写，会出错
		EvenChecker.test(new MutexEvenGenerator());// 用Lock同步
	}

}
