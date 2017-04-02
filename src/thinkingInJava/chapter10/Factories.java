package thinkingInJava.chapter10;

public class Factories {
	
	/**
	 * 工厂生产产品，并使用产品。将流程与工厂/产品的具体实现分开，解耦
	 * @param factory
	 */
	public static void serviceConsumer(ServiceFactory factory){
		Service service = factory.getService();
		service.method1();
		service.method2();
	}
	
	public static void main(String[] args) {
		serviceConsumer(ServiceImpl1.factory);
	}
}
