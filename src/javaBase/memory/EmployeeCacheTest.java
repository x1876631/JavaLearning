package javaBase.memory;

import utils.PrintUtils;

/**
 * Created by xuye on 2017年11月19日
 * <p>
 * 软引用实现缓存的测试类
 */
public class EmployeeCacheTest {

	public static void main(String[] args) {

		String test_id_1 = "1";
		String test_id_2 = "2";

		// 创建一个缓存实例
		EmployeeCache cache = EmployeeCache.getInstance();

		System.out.println("----test start----\n");
		long startTime = System.currentTimeMillis();
		getEmployeeInfo(cache, test_id_1);
		PrintUtils.printRunTime("k1", System.currentTimeMillis() - startTime);

		startTime = System.currentTimeMillis();
		getEmployeeInfo(cache, test_id_2);
		PrintUtils.printRunTime("k2", System.currentTimeMillis() - startTime);

		startTime = System.currentTimeMillis();
		getEmployeeInfo(cache, test_id_1);
		PrintUtils.printRunTime("k3", System.currentTimeMillis() - startTime);

		startTime = System.currentTimeMillis();
		getEmployeeInfo(cache, test_id_2);
		PrintUtils.printRunTime("k4", System.currentTimeMillis() - startTime);
		cache.clearCache();

		System.out.println("----after clear cache----\n");
		startTime = System.currentTimeMillis();
		getEmployeeInfo(cache, test_id_1);
		PrintUtils.printRunTime("k5", System.currentTimeMillis() - startTime);

		startTime = System.currentTimeMillis();
		getEmployeeInfo(cache, test_id_2);
		PrintUtils.printRunTime("k6", System.currentTimeMillis() - startTime);

		System.out.println("----test end----\n");
	}

	private static void getEmployeeInfo(EmployeeCache cache, String id) {
		System.out.println("打印雇员" + cache.getEmployee(id).getID() + "信息");
	}
}
