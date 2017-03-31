package thinkingInJava.chapter5;

/**
 * Created by xuye on 2017-03-30
 * 
 * 类初始化测试，结果是531426
 * 53在前是肯定的，因为编译时会初始化静态成员变量
 * 1在426前，是因为创建InitializationTest对象时，有参数，先执行了参数的创建，即先执行了Value的初始化
 * 接下来是42，即初始化非静态的类成员变量
 * 最后是6，因为构造器是在上诉操作都执行完以后才执行的
 */
public class InitializationTest {

	private Value mValue1;
	private Value mValue4;
	private Value mValue6;

	{
		// 实例化块，放在1和2的间，static前面，以便了解变量初始化顺序
		mValue4 = new Value(4);
	}

	private Value mValue2 = new Value(2);

	public InitializationTest() {
	}

	public InitializationTest(Value value) {
		this.mValue1 = value;
		this.mValue6 = new Value(6);
	}

	// 把静态变量放在后面，看输出时的顺序，以便了解变量初始化顺序
	private static Value mValue3;
	private static Value mValue5 = new Value(5);

	static {
		// 静态代码块
		mValue3 = new Value(3);
	}

	public static void main(String[] args) {
		InitializationTest test = new InitializationTest(new Value(1));
	}
}
