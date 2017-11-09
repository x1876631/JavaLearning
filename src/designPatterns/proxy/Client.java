package designPatterns.proxy;

/**
 * created by xuye on 2017年11月9日
 * 
 * 静态代理模式的操作过程演示
 */
public class Client {
	public static void main(String[] args) {
		ILawsuit staff = new Staff();
		ILawsuit lawyer = new Lawyer(staff);
		lawyer.submit();
		lawyer.burden();
		lawyer.defend();
		lawyer.finished();
	}
}
