package javaIO;

import java.io.File;
import java.io.IOException;

/**
 * Created by xuye on 2016/10/30
 * java File类学习
 */
public class FileLearning {

	public static void main(String[] args) {
		//.代表当前目录，即这个类所在工程的目录，也就是javaLearning这个文件夹的路径
		File file = new File(".");
		try {
			System.out.println("file的绝对路径："+file.getAbsolutePath());
			System.out.println("file的规范路径名(去掉了绝对路径里多余的符号)："+file.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//打印file下的文件列表
		String files[] = file.list();
		for (String fileString : files) {
			System.out.println(fileString);
		}
	}

}
