package thinkingInJava.chapter18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by xuye on 2017-6-27
 * <p>
 * 从标准输入中读取
 */
public class Echo {
	public static void main(String[] args) throws IOException {
		// 把控制台输入包装起来
		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));
		String s;
		// 逐行读取输入，如果有值就输出，如果没有值就结束程序了
		while ((s = stdin.readLine()) != null && s.length() > 0) {
			System.out.println(s);
		}
	}
}
