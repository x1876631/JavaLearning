package designPatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * created by xuye on 2017年11月9日
 * 
 * 对代理的接口方法做处理
 */
public class DynamicProxy implements InvocationHandler {

	private Object mObj;// 被代理的对象，可以是任何需要被代理的类

	public DynamicProxy(Object object) {
		this.mObj = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

		// 当调用代理类方法的时候，做了些扩展功能
		System.out.println("Before invocation"); // 预处理
		Object result = method.invoke(mObj, args);// 使用反射，调用具体的被代理方法
		System.out.println("After invocation\n"); // 事后处理
		return result;
	}

}
