package thinkingInJava.chapter8;

/**
 * Created by xuye on 2017-03-31
 *
 * 父测试类
 */
public class SuperClass {
	
	public int commonValue = 1;
	private int super1 = printInit("SuperClass.super1 initialized");
	protected int super2;

	public SuperClass() {
		System.out.println("super1 = " + super1 + " , super2 = " + super2);
		super2 = 39;
	}
	private static int super3 = printInit("static SuperClass.super3 initialized");
	static int printInit(String s){
		System.out.println(s);
		return 47;
	} 
}
