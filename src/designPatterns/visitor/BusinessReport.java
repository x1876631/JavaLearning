package designPatterns.visitor;

import java.util.LinkedList;
import java.util.List;

/**
 * created by xuye on 2017年12月1日
 * 
 * 员工业务报表 将数据对象staff和数据操作visit()分离，如果不使用访问者模式，则必须对每种staff做if else类型判断和转换
 */
public class BusinessReport {
	List<Staff> mStaffs = new LinkedList<>();

	public BusinessReport() {
		mStaffs.add(new Manager("经理1"));
		mStaffs.add(new Engineer("工程师1"));
		mStaffs.add(new Engineer("工程师2"));
	}

	public void showReport(Visitor visitor) {
		// 不同员工，接受不同访问者的访问，做出不同的处理
		System.out.println("----使用访问者模式，对visitor做处理----");
		for (Staff staff : mStaffs) {
			staff.accept(visitor);
		}

		// 如果不同访问者模式，需要对staff做if判断，并做类型转换【写起来太恶心了！】
		System.out.println("----不使用访问者模式，对visitor做if判断和转型----");
		for (Staff staff : mStaffs) {
			if (visitor instanceof CEOVisitor) {
				if (staff instanceof Manager) {
					Manager manager = (Manager) staff;
					System.out.println(
							"经理 :" + manager.mName + " ，KPI: " + manager.mKpi + "，新产品数量: " + manager.getProducts());
				} else if (staff instanceof Engineer) {
					Engineer engineer = (Engineer) staff;
					System.out.println("工程师 :" + engineer.mName + " ，KPI: " + engineer.mKpi);
				}
			} else if (visitor instanceof CTOVisitor) {
				if (staff instanceof Manager) {
					Manager manager = (Manager) staff;
					System.out.println("经理 :" + manager.mName + "，产品数量: " + manager.getProducts());
				} else if (staff instanceof Engineer) {
					Engineer engineer = (Engineer) staff;
					System.out.println("工程师 :" + engineer.mName + "，代码数量: " + engineer.getCodeLines() + "行");
				}
			}
		}

	}

	private void handleVisitor(Visitor visitor) {
		if (visitor instanceof CEOVisitor) {

		}
	}
}
