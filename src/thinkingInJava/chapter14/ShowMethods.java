package thinkingInJava.chapter14;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * Created by xuye on 2017-4-9
 * 
 * 方法查找工具类，用于学习反射
 *
 * 在命令行里，先执行：javac
 * /Users/xuye/Documents/workspace/javaLearning/src/thinkingInJava/chapter14
 * /ShowMethods.java
 * 
 * 再执行：java thinkingInJava/chapter14/ShowMethods
 * "thinkingInJava.chapter14.ShowMethods" "String"
 */
public class ShowMethods {
	private static Pattern p = Pattern.compile("\\w+\\.");// 这个原本是要把方法前面的包名去掉用的

	/**
	 * @param args
	 *            第一个args为要反射的类的全限定名，第2个args为查找限定词
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("no args!");
			System.exit(0);
		}
		try {
			// 根据传入的类名找到该类的Class对象
			Class<?> c = Class.forName(args[0]);
			// 从Class对象那里获取方法列表和构造器列表
			Method[] methods = c.getMethods();
			Constructor[] constructors = c.getConstructors();
			if (args.length == 1) {
				System.out.println("method list : ");
				for (Method method : methods) {
					System.out.println(method.toString());
				}
				System.out.println();
				for (Constructor constructor : constructors) {
					System.out.println("constructor list : ");
					System.out.println(constructor.toString());
				}
			} else {
				System.out.println("method list : ");
				for (Method method : methods) {
					// 如果有多余1个的参数，可以根据第2个参数，缩小查找方法的范围
					// 比如我们要找关于返回值或者参数是string的方法，如果发现方法特征签名里没有string，就不打印了
					if (method.toString().indexOf(args[1]) != -1) {
						System.out.println(p.matcher(method.toString())
								.replaceAll(""));
					}
				}
				System.out.println();
				for (Constructor constructor : constructors) {
					System.out.println("constructor list : ");
					if (constructor.toString().indexOf(args[1]) != -1) {
						System.out.println(p.matcher(constructor.toString())
								.replaceAll(""));
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
