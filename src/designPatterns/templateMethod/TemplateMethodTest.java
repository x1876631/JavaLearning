package designPatterns.templateMethod;

/**
 * created by xuye on 2017年11月6日
 * 
 * 模板方法实例测试类
 */
public class TemplateMethodTest {

	public static void main(String[] args) {
		AbstractComputer computer = new CoderComputer();
		// 程序员计算机开机
		computer.startUp();

		computer = new MilitaryComputer();
		// 军用计算机开机
		computer.startUp();
	}
}
