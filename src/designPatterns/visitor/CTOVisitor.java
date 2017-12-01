package designPatterns.visitor;

/**
 * created by xuye on 2017年12月1日
 * 
 * cto访问者，只关注技术与产品上的贡献
 */
public class CTOVisitor implements Visitor {

	@Override
	public void visit(Engineer engineer) {
		System.out.println("工程师 :" + engineer.mName + "，代码数量: " + engineer.getCodeLines() + "行");
	}

	@Override
	public void visit(Manager manager) {
		System.out.println("经理 :" + manager.mName + "，产品数量: " + manager.getProducts());
	}

}
