package thinkingInJava.chapter13;

import java.util.Scanner;

public class ScannerTest {
	public static final String TEST_STRING = "abc\n12 3 1.23456";
	public static final String TEST_STRING2 ="12, 34, 56";
	public static void main(String[] args) {
		//Scanner 扫描输入类，使用nextXX获取分组数据，默认以空格作为定界符
		Scanner scanner = new Scanner(TEST_STRING);
		System.out.println("name : "+scanner.nextLine());
		System.out.println("age : "+scanner.nextInt());
		System.out.println("price : "+scanner.nextDouble());
		
		//使用自定义定界符分组
		scanner= new Scanner(TEST_STRING2);
		scanner.useDelimiter("\\s*,\\s*");
		while(scanner.hasNextInt()){
			System.out.println(scanner.nextInt());
		}
	}
}
