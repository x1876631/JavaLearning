package designPatterns.templateMethod;

/**
 * created by xuye on 2017年11月6日
 * 
 * 模板方法子类1，程序员的计算机
 */
public class CoderComputer extends AbstractComputer {
	@Override
	protected void login() {
		System.out.println("4、验证用户名和密码");
	}
}
