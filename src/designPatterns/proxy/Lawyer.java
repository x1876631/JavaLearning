package designPatterns.proxy;

/**
 * created by xuye on 2017年11月9日
 * 
 * 代理模式里的代理类，这里以一个帮人打官司的律师为例
 */
public class Lawyer implements ILawsuit {
	private ILawsuit mLawsuit;// 持有一个具体的被代理者的引用

	public Lawyer(ILawsuit lawsuit) {
		// 该代理者有和被代理者相同的操作，当操作该代理类时，其实就是操作被代理者做同样的动作
		this.mLawsuit = lawsuit;
	}

	@Override
	public void submit() {
		// 预处理...
		mLawsuit.submit();
		// 事后处理...
	}

	@Override
	public void burden() {
		mLawsuit.burden();
	}

	@Override
	public void defend() {
		mLawsuit.defend();
	}

	@Override
	public void finished() {
		mLawsuit.finished();
	}
}
