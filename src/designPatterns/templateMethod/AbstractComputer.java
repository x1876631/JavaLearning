package designPatterns.templateMethod;

/**
 * created by xuye on 2017年11月6日
 * 
 * 模板方法，通常适用于知道一个算法的关键步骤并确定了执行顺序，但是步骤的具体实现未知或者随环境变化
 * 
 * 使用场景：重构时，相同的流程抽取到父类，然后通过让子类各自实现(自己重写或者抽象方法强制重写)，约束子类流程
 * 
 * 计算机抽象类，封装了开机流程和流程步骤基础实现
 */
public abstract class AbstractComputer {
	protected void powerOn() {
		System.out.println("1、开启电源");
	}

	protected void checkHardware() {
		System.out.println("2、硬件检查");
	}

	protected void loadOS() {
		System.out.println("3、载入操作系统");
	}

	protected void login() {
		System.out.println("4、普通计算机无验证，直接登录");
	}

	/**
	 * startUp定义了一个流程框架(核心算法)，使用final防止子类修改，流程固定为4步，子类可以自定义实现每步的具体操作
	 */
	public final void startUp() {
		System.out.println("------开机流程 start------");
		powerOn();
		checkHardware();
		loadOS();
		login();
		System.out.println("------开机流程 end------");
	}
}
