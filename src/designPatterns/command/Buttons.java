package designPatterns.command;

/**
 * created by xuye on 2017年12月1日
 * 
 * 命令模式里的调用者，本来应该直接用他去调用执行者，但是命令模式增加了一层命令，解开了2者间的耦合
 * 
 * 这里以游戏按钮为例
 */
public class Buttons {
	private LeftCommand mLeftCommand;
	private RightCommand mrRightCommand;

	public void setLeftCommand(LeftCommand leftCommand) {
		this.mLeftCommand = leftCommand;
	}

	public void setRightCommand(RightCommand rightCommand) {
		this.mrRightCommand = rightCommand;
	}

	public void toLeft() {
		mLeftCommand.execute();
	}

	public void toRight() {
		mrRightCommand.execute();
	}
}
