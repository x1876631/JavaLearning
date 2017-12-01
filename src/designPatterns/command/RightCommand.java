package designPatterns.command;

/**
 * created by xuye on 2017年12月1日
 */
public class RightCommand implements Command {

	private TetrisMachine mTetrisMachine;

	public RightCommand(TetrisMachine machine) {
		this.mTetrisMachine = machine;
	}

	@Override
	public void execute() {
		mTetrisMachine.toRight();
	}

}
