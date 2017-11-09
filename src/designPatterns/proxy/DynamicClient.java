package designPatterns.proxy;

import java.lang.reflect.Proxy;

/**
 * created by xuye on 2017年11月9日
 * 
 * 动态代理演示过程，没有让我们再去像静态代理那样自己写代理类，而是由一个统一的动态代理去做代理动作
 */
public class DynamicClient {

	public static void main(String[] args) {
		ILawsuit staff = new Staff();// 某个被代理者
		DynamicProxy proxy = new DynamicProxy(staff);// 动态代理者，无论哪个被代理者，都用这个代理
		ClassLoader loader = staff.getClass().getClassLoader();
		// 这里根据传入的被代理类，和实现了代理类创建接口的动态代理，生出了一个具体的代理类lawyer
		ILawsuit lawyer = (ILawsuit) Proxy.newProxyInstance(loader, new Class[] { ILawsuit.class }, proxy);

		lawyer.submit();
		lawyer.burden();
		lawyer.defend();
		lawyer.finished();
	}

}
