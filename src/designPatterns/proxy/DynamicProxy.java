package designPatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * created by xuye on 2017年11月9日
 * 
 * 动态代理类
 */
public class DynamicProxy implements InvocationHandler {

	private Object mObj;// 被代理的对象，可以是任何需要被代理的类

	public DynamicProxy(Object object) {
		this.mObj = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 调用具体的被代理方法
		Object result = method.invoke(mObj, args);
		return result;
	}

}
