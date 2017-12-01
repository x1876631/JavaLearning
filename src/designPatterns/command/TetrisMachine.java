package designPatterns.command;

/**
 * created by xuye on 2017年12月1日
 * 
 * 命令模式的执行者，其他类都直接或者间接的调用这个类
 * 
 * 这里以俄罗斯方块游戏本身为例
 */
public class TetrisMachine {
	public void toLeft() {
		System.out.println("向左");
	}

	public void toRight() {
		System.out.println("向右");
	}

	public void fastToBottom() {
		System.out.println("快速下落");
	}

	public void transform() {
		System.out.println("改变形状");
	}
}
