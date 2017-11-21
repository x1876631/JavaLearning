package designPatterns.observer;

/**
 * created by xuye on 2017年11月21日
 * 
 * 观察者模式的测试类，将具体的观察者Coder和具体的被观察者DevTechFrontier解耦
 */
public class ObserberTest {
	public static void main(String[] args) {
		DevTechFrontier devTechFrontier = new DevTechFrontier();
		Coder coder1 = new Coder("coder-1");
		Coder coder2 = new Coder("coder-2");

		devTechFrontier.addObserver(coder1);
		devTechFrontier.addObserver(coder2);

		devTechFrontier.postNewPublication("xxx");
	}
}
