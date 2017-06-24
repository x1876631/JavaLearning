package thinkingInJava.chapter18;

import java.io.File;

/**
 * Created by xuye on 2017-6-24 目录的检查与创建
 * <p>
 * 注意，mac系统执行java文件先javac ./MakeDirectories.java，然后再进入src目录下，执行java
 * thinkingInJava/chapter18/MakeDirectories "filetext.txt"
 */
public class MakeDirectories {
	private static void fileData(File f) {
		System.out.println("Absolute path: " + f.getAbsolutePath()
				+ "\n Can read: " + f.canRead() + "\n Can write: "
				+ f.canWrite() + "\n getName: " + f.getName()
				+ "\n getParent: " + f.getParent() + "\n getPath: "
				+ f.getPath() + "\n length: " + f.length()
				+ "\n lastModified: " + f.lastModified());
		if (f.isFile()) {
			System.out.println("It's a file");
		} else if (f.isDirectory()) {
			System.out.println("It's a directory");
		}
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("no args!");
			return;
		}
		if (args[0].equals("-r") && args.length == 3) {
			// 重命名文件，需要3个参数，-r oldname rname
			File old = new File(args[1]), rname = new File(args[2]);
			old.renameTo(rname);
			// 打印新老文件的文件属性信息
			fileData(old);
			fileData(rname);
			return;
		}
		int count = 0;
		boolean del = false;
		if (args[0].equals("-d")) {
			count++;
			del = true;
		}
		while (count < args.length) {
			System.out.println("index: " + count + " , arg: " + args[count]);
			File file = new File(args[count]);
			// 对指定路径的文件，做删除或者创建操作
			if (file.exists()) {
				// 如果存在该文件夹，且当前要求执行删除操作，则删除该文件夹
				System.out.println(file + " exists");
				if (del) {
					System.out.println("deleting..." + file);
					file.delete();
				}
			} else {
				// 如果文件夹不存在，且不是删除，则创建该文件夹
				if (!del) {
					file.mkdir();
					System.out.println("create " + file);
				}
			}
			fileData(file);
			count++;
		}
	}
}
