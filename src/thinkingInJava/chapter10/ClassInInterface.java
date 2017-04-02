package thinkingInJava.chapter10;

/**
 * Created by xuye on 2017-4-2
 *	
 * 接口里虽然只允许有常量和抽象方法，但是可以嵌入静态内部类，以创建公共代码，被该接口的所有不同实现共用
 */
public interface ClassInInterface {
	int value = 1;
	void commonMethod1();
	void commonMethod2();
	
	
	/**
	 * Created by xuye on 2017-4-2
	 * 
	 * 该类可以作为接口的公共实现被使用
	 */
	class CommonClass implements ClassInInterface{

		@Override
		public void commonMethod1() {
			System.out.println("call out method");
			commonMethod2();
		}
		
		public static void main(String[] args) {
			new CommonClass().commonMethod1();
		}
		
		@Override
		public void commonMethod2() {
			
		}
		
	}
}
