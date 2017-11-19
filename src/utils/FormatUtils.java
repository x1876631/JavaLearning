package utils;

public class FormatUtils {
	/**
	 * 把double转换成string，一般用于保留doublexx位的展示，最多保留6位
	 *
	 * @param doubleContent
	 *            double值
	 * @param count
	 *            保留count位
	 * @return 装换后的值
	 */
	public static String formatDoubleToString(double doubleContent, int count) {
		String result = "";
		count = Math.abs(count);// 保证都是正数
		if (count > 6) {
			count = 6;
		}
		// 对doubleContent处理的正则：%.xf，x代表保留几位。
		String formatString = "%." + count + "f";
		result = String.format(formatString, doubleContent);
		return result;
	}

}
