package thinkingInJava.chapter14;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HiddenImpl {

	public static void main(String[] args) throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchFieldException {
		A a = HiddenC.makeA();
		a.f();
		System.out.println("a name: " + a.getClass().getName() + "\n");

		if (a instanceof C) {
			System.out.println("a instance C");
			// 基类a被向下转型成c了
			C c = (C) a;
			c.g();
			// c.w();//隐藏起来的private方法调用不了
		}
		callHiddenMethod(a, "w");// 使用反射调用方法
		System.out.println("c.s1 : "+((C)a).s1);
		callHiddenField(a, "s1");// 使用反射调用字段
		System.out.println("c.s1 : "+((C)a).s1);
	}

	/**
	 * 使用反射调用a对象的方法
	 * 
	 * @param a
	 *            要调用的对象
	 * @param methodName
	 *            要调用的方法
	 */
	static void callHiddenMethod(Object a, String methodName)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Method w = a.getClass().getDeclaredMethod(methodName);
		w.setAccessible(true);
		w.invoke(a);
	}

	/**
	 * 使用反射操作a对象的字段
	 * 
	 * @param a
	 *            要调用的对象
	 * @param fieldName
	 *            要操作的字段
	 */
	static void callHiddenField(Object a, String fieldName)
			throws NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		Field w = a.getClass().getDeclaredField(fieldName);
		w.setAccessible(true);
		System.out.println("field content: "+w.get(a).toString());
		w.set(a, "I update the private field-s");
		System.out.println("field content: "+w.get(a).toString());
	}
}
