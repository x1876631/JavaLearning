package designPatterns.command;

/**
 * created by xuye on 2017年12月1日
 * 
 * 对实际执行者做了层封装，这个类的调用代码不用改，直接改TetrisMachine就好了
 */
public class LeftCommand implements Command {

	private TetrisMachine mTetrisMachine;

	public LeftCommand(TetrisMachine machine) {
		this.mTetrisMachine = machine;
	}

	@Override
	public void execute() {
		mTetrisMachine.toLeft();
	}

}
