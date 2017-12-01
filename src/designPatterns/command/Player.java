package designPatterns.command;

/**
 * created by xuye on 2017年12月1日
 * 
 * 命令模式测试类
 */
public class Player {
	public static void main(String[] args) {
		TetrisMachine machine = new TetrisMachine();

		Buttons buttons = new Buttons();
		buttons.setLeftCommand(new LeftCommand(machine));
		buttons.setRightCommand(new RightCommand(machine));

		buttons.toLeft();
		buttons.toRight();
	}
}
