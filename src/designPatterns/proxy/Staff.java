package designPatterns.proxy;

/**
 * created by xuye on 2017年11月9日
 * 
 * 代理模式下的被代理者
 * 
 * 这里以一个被拖欠工资的员工的申诉过程为例，他不会自己去打官司，需要找律师代他打官司
 */
public class Staff implements ILawsuit {

	@Override
	public void submit() {
		System.out.println("申诉：老板拖欠工资，申请仲裁");
	}

	@Override
	public void burden() {
		System.out.println("举证：合同和工资流水");
	}

	@Override
	public void defend() {
		System.out.println("辩护：证据确凿，无话可说");
	}

	@Override
	public void finished() {
		System.out.println("结案：胜诉，尽快结清拖欠工资");
	}

}
