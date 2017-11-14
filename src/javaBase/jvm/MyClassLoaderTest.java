package javaBase.jvm;

/**
 * Created by xuye on 2017年11月14日
 * <p>
 * 自定义classloader测试，能否正常加载指定的类，和比较不同的加载器去加载同一份class是否生成一个类
 */
public class MyClassLoaderTest {
	public static void main(String[] args) throws Exception {
		MyClassLoader classLoader = new MyClassLoader();
		Object object = classLoader.loadClass("javaBase.jvm.MyClassLoaderTest")
				.newInstance();
		System.out.println("自定义加载器加载的类名: " + object.getClass());
		System.out.println("自定义加载器加载出的Test 和 系统加载器加载的Test是一个类吗？"
				+ (object instanceof javaBase.jvm.MyClassLoaderTest));
	}
}
