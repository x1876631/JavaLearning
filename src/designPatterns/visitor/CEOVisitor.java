package designPatterns.visitor;

/**
 * created by xuye on 2017年12月1日
 * 
 * ceo访问者，只关注KPI业绩
 */
public class CEOVisitor implements Visitor {

	@Override
	public void visit(Engineer engineer) {
		System.out.println("工程师 :" + engineer.mName + " ，KPI: " + engineer.mKpi);
	}

	@Override
	public void visit(Manager manager) {
		System.out.println("经理 :" + manager.mName + " ，KPI: " + manager.mKpi + "，新产品数量: " + manager.getProducts());
	}

}
