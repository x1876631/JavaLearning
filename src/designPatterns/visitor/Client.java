package designPatterns.visitor;

/**
 * created by xuye on 2017年12月1日
 */
public class Client {
	public static void main(String[] args) {
		BusinessReport report = new BusinessReport();
		System.out.println("========给CEO看的报告========");
		report.showReport(new CEOVisitor());
		System.out.println("========给CTO看的报告========");
		report.showReport(new CTOVisitor());
	}
}
