package thinkingInJava.chapter18;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList {

	public static void main(String[] args) {
		// .表示当前项目的目录路径，完整路径是：/Users/xuye/Documents/workspace/javaLearning/.
		File path = new File(".");
		System.out.println(path.getAbsolutePath());

		String[] list;
		if (args.length == 0) {
			list = path.list();
		} else {
			list = path.list(new DirFilter(args[0]));
		}
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		// 所以这个list的内容就是当前项目下的文件和文件夹：bin、README.md、src和一些隐藏文件
		for (String dirItem : list) {
			System.out.println(dirItem);
		}
	}

}

/**
 * created by xuye 文件筛选类，筛选条件是初始化时传入的字符串regex
 */
class DirFilter implements FilenameFilter {

	private Pattern pattern;

	public DirFilter(String regex) {
		pattern = Pattern.compile(regex);
	}

	/**
	 * 是否符合筛选条件
	 * 
	 * @param dir
	 *            file
	 * @param name
	 *            要被筛选的file名
	 * @return 是否符合筛选条件
	 */
	@Override
	public boolean accept(File dir, String name) {
		return pattern.matcher(name).matches();
	}

}
