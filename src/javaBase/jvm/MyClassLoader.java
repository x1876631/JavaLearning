package javaBase.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xuye on 2017年11月14日
 * <p>
 * 自定义的classloader，重写了类加载的方法，打破了双亲委托机制
 */
public class MyClassLoader extends ClassLoader {

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		// 自定义loadClass可以自己指定加载机制
		// return super.loadClass(name);
		try {
			String filename = name.substring(name.lastIndexOf(".") + 1)
					+ ".class";
			InputStream is = getClass().getResourceAsStream(filename);
			if (is == null) {
				// 本地找不到指定的类，才使用父类的加载器
				return super.loadClass(name);
			} else {
				// 如果本地找到了指定的类，则使用我们自定义的类加载类去加载指定的类
				// 其实就是读取找到的.class文件，然后创建了一个Class对象并返回
				byte[] b;

				b = new byte[is.available()];
				is.read(b);

				return defineClass(name, b, 0, b.length);
			}
		} catch (IOException e) {
			throw new ClassNotFoundException(name);
		}
	}
}
