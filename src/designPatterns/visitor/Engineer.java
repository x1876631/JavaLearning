package designPatterns.visitor;

import java.util.Random;

/**
 * created by xuye on 2017年12月1日
 */
public class Engineer extends Staff {

	public Engineer(String name) {
		super(name);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	/**
	 * @return 工程师一年的代码量
	 */
	public int getCodeLines() {
		return new Random().nextInt(10 * 10000);
	}

}
