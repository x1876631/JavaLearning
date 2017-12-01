package designPatterns.visitor;

import java.util.Random;

/**
 * created by xuye on 2017年12月1日
 */
public class Manager extends Staff {

	private int products;// 产品数量

	public Manager(String name) {
		super(name);
		products = new Random().nextInt(10);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public int getProducts() {
		return products;
	}
}
