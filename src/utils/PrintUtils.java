package utils;

/**
 * Created by xuye on 2017-4-2
 *
 * 打印工具类
 */
public class PrintUtils {
	public static int printInit(String printContent, int initValue) {
		System.out.println(printContent);
		return initValue;
	}

	/**
	 * 打印操作耗时
	 * 
	 * @param operationName
	 *            操作名称
	 * @param runTime
	 *            消耗的ms数
	 */
	public static void printRunTime(String operationName, long runTime) {
		System.out.println(operationName + "操作耗时："
				+ FormatUtils.formatDoubleToString(((runTime) / 1000.0), 0)
				+ "s\n");
	}
}
