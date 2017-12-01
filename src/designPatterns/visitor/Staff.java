package designPatterns.visitor;

import java.util.Random;

/**
 * created by xuye on 2017年12月1日
 */
public abstract class Staff {
	public String mName;
	public int mKpi;

	public Staff(String name) {
		this.mName = name;
		mKpi = new Random().nextInt(10);
	}

	/**
	 * 接受访问者的访问
	 */
	public abstract void accept(Visitor visitor);
}
