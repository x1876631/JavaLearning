package designPatterns.templateMethod;

/**
 * created by xuye on 2017年11月6日
 * 
 * 模板方法子类2，军用计算机
 */
public class MilitaryComputer extends AbstractComputer {
	@Override
	protected void checkHardware() {
		super.checkHardware();
		System.out.println("2、检查硬件防火墙");
	}

	@Override
	protected void login() {
		System.out.println("4、指纹识别登录");
	}
}
