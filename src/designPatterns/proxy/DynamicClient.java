package designPatterns.proxy;

import java.lang.reflect.Proxy;

/**
 * created by xuye on 2017年11月9日
 * 
 * 静态代理有缺点，就是当需要被代理的类增多时，代理类也跟着增多，我们可以用动态代理来创建代理类<br/>
 * 动态代理演示过程，没有让我们再去像静态代理那样自己写代理类，而是由一个统一的动态代理去做代理动作
 */
public class DynamicClient {

	public static void main(String[] args) {
		ILawsuit staff = new Staff();// 某个被代理者
		DynamicProxy proxy = new DynamicProxy(staff);// 被代理接口的动态扩展类，里面定义了对被代理方法的扩展逻辑
		ClassLoader loader = staff.getClass().getClassLoader();
		// 这里动态生成了一个动态代理类，里面有传入接口ILawsuit的所有方法实现
		ILawsuit lawyer = (ILawsuit) Proxy.newProxyInstance(loader,
				new Class[] { ILawsuit.class }, proxy);

		/**
		 * 动态生产的lawyer代理类，里面实现了ILawsuit接口的一些方法，
		 * 这些方法的实现是调用InvocationHandler的invoke方法<br/>
		 */
		lawyer.submit();
		lawyer.burden();
		lawyer.defend();
		lawyer.finished();
	}

}
