package javaBase.jvm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import javaBase.jvm.ClassForReflectionTest.Color;

/**
 * Created by xuye on 2017年11月14日
 * <p>
 * 反射的学习
 */
public class ReflectionTest {

	public static void main(String[] args) {
		ReflectionTest test = new ReflectionTest();
		// test.normalRunTest();

		test.ReflectionRunTest();
	}

	/**
	 * 使用正常的方式，调用测试类的方法
	 * 
	 * 耗时约1ms
	 */
	private void normalRunTest() {
		long beginTime = System.currentTimeMillis();
		ClassForReflectionTest test = new ClassForReflectionTest();
		test.run();
		System.out.println("正常调用的耗时："
				+ (System.currentTimeMillis() - beginTime) + "ms");
	}

	/**
	 * 使用反射的方式执行某测试类方法
	 * 
	 * 耗时约2ms，运行速度慢，就是反射强大的代价
	 */
	private void ReflectionRunTest() {
		long beginTime = System.currentTimeMillis();

		// 获取测试类的Class对象
		Class cls = ClassForReflectionTest.class;

		Constructor testConstructor = null;
		try {
			// 获取指定参数类型的构造方法
			testConstructor = cls.getConstructor(String.class, Color.class);
			if (testConstructor != null) {
				// 利用反射，创建测试类对象实例
				ClassForReflectionTest test = (ClassForReflectionTest) testConstructor
						.newInstance("test", Color.RED);

				// 通过Class对象，获取指定名称的方法对象
				Method testMethod = cls.getMethod("run");

				// 调用使用反射获取的方法
				testMethod.invoke(test, null);

				System.out.println("反射调用的耗时："
						+ (System.currentTimeMillis() - beginTime) + "ms");

				testGetParameterTypes(cls);
			}
		} catch (Exception e) {
			System.out.println("----反射测试过程出现异常----");
			e.printStackTrace();
		}
	}

	public void testGetParameterTypes(Class cls) {
		System.out.println("----测试 method.getParameterTypes()----");
		// 找个带参数的方法
		Method[] methods = cls.getDeclaredMethods();

		// 看看getParameterTypes里面是什么？
		for (Method method : methods) {
			System.out.println("method name: " + method.getName());
			Class<?>[] parameterTypes = method.getParameterTypes();
			if (parameterTypes != null && parameterTypes.length > 0) {
				System.out.println("parameterTypes[0]:" + parameterTypes[0]);
			}

			for (Class<?> class1 : parameterTypes) {
				System.out.print("方法参数：" + class1.getName() + "  ");
			}
			System.out.println("\n");
		}
	}
}
