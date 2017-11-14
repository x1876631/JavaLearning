package javaBase.jvm;

/**
 * Created by xuye on 2017年11月14日
 * <p>
 * Class对象的获取方法
 */
public class GetClassTest {
	public static void main(String[] args) {

		// 方法1：创建某个类的实例后，调用实例的getClass()方法，但是无法用于基础类型
		// SuperClass superClass = new SuperClass();
		// Class class1 = superClass.getClass();
		// System.out.println("class1 引用类型getName：" + class1.getName());
		Class class11 = new String[0].getClass();
		System.out.println("class11 数组类型getName：" + class11.getName());

		// 方法2：使用不创建实例的方法，获取Class对象，只要使用.class标识就好，可以获取基础类型的Class对象
		// 另外，这种方法不会进行类的初始化，而1和3会初始化，设置类static值和执行static块
		Class class2 = int.class;
		System.out.println("class2 基础类型getName：" + class2.getName());

		// 方法3：使用Class类的静态方法forNname(全限定名，就是包名+类名)去获取指定的类Class对象
		String classname = "javaBase.jvm.SuperClass";
		try {
			Class class3 = Class.forName(classname);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("ClassNotFoundException : " + classname);
		}
	}
}
