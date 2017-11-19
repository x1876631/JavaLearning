package javaBase.memory;

/**
 * Created by xuye on 2017年11月19日
 * <p>
 * 雇员信息类
 */
public class Employee {
	private String id;// 雇员的标识号码
	private String name;// 雇员姓名
	private String department;// 该雇员所在部门
	private String Phone;// 该雇员联系电话
	private int salary;// 该雇员薪资
	private String origin;// 该雇员信息的来源

	// 构造方法，每次都需要去查询和创建一个对象，耗时，耗内存
	public Employee(String id) {
		this.id = id;
		getDataFromlnfoCenter();
	}

	/**
	 * 到数据库中取得雇员信息
	 */
	private void getDataFromlnfoCenter() {
		// 通过sleep来模拟从数据库或者文件中查信息的耗时操作
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("sleep error");
			e.printStackTrace();
		}
	}

	public String getID() {
		return id;
	}
}
