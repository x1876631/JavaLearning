package javaBase.jvm;

/**
 * Created by xuye on 2017年11月14日
 * <p>
 * 为了学习反射测试用的类
 */
public class ClassForReflectionTest {
	private static final String tag = "反射测试：";

	public enum Color {
		WHITE, RED, BLUE
	}

	private String mVendor;
	private Color mColor;

	/**
	 * 无参构造方法
	 */
	public ClassForReflectionTest() {
		mVendor = "defaultVendor";
		mColor = Color.WHITE;
	}

	/**
	 * 有参构造方法
	 */
	public ClassForReflectionTest(String vendor, Color color) {
		super();
		mVendor = vendor;
		mColor = color;
	}

	/**
	 * 测试过程
	 */
	public void run() {
		testStepBegin();
		testStep1();
		testStep2();
		testStep3();
		testStepEnd();
	}

	private void testStepBegin() {
		System.out.println(tag + "开始");
	}

	private void testStep1() {
		System.out.println(tag + "step1");
	}

	private void testStep2() {
		System.out.println(tag + "step2");
	}

	private void testStep3() {
		System.out.println(tag + "step3");
	}

	private void testStepEnd() {
		System.out.println(tag + "结束");
	}
}
