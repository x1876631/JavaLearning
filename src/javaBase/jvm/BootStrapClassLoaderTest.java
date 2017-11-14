package javaBase.jvm;

import java.net.URL;

/**
 * Created by xuye on 2017年11月14日
 * <p>
 * BootStrapClassLoader(引导)启动类加载器，java类加载层次中最顶级的类加载器，用于加载JDK中的核心类库，即<JAVA_HOME>/
 * lib下的库。 这个类加载器不继承自java.lang.ClassLoader，而是由c++实现，是jvm的一部分。当jvm启动后，该类加载器也随之启动，
 * 加载java的核心类库，并构造其他2个类加载器。
 * 
 * 还有其他2个类加载器，Extension ClassLoader和Application ClassLoader
 * 
 * Extension ClassLoader：扩展类加载器，用于加载java的扩展类库，<JAVA_HOME>/lib/ext下面的库
 * Application ClassLoader：应用程序类(系统类)加载器，加载classpath(用户类路径)下的类，java成默认使用的类加载器
 */
public class BootStrapClassLoaderTest {
	public static void main(String[] args) {
		// 这里使用sun.misc.Launcher.getBootstrapClassPath()原本报错，说是无权访问，修改一下eclipse的warning配置就好了
		// 文章见：http://blog.csdn.net/u014470581/article/details/51351944
		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for (int i = 0; i < urls.length; i++) {
			System.out.println(urls[i].toExternalForm());
		}
		/*
		 * 输出的结果：
		 * file:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents
		 * /Home/jre/lib/resources.jar
		 * 
		 * file:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre
		 * /lib/rt.jar
		 * 
		 * file:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre
		 * /lib/sunrsasign.jar
		 * 
		 * file:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre
		 * /lib/jsse.jar
		 * 
		 * file:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre
		 * /lib/jce.jar
		 * 
		 * file:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre
		 * /lib/charsets.jar
		 * 
		 * file:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre
		 * /lib/jfr.jar
		 * 
		 * file:/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre
		 * /classes
		 */

	}
}
