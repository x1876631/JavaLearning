package designPatterns.visitor;

/**
 * created by xuye on 2017年12月1日
 */
public interface Visitor {
	void visit(Engineer engineer);

	void visit(Manager manager);
}
