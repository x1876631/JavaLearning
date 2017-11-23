package designPatterns.facade;

/**
 * created by xuye on 2017年11月23日
 * 
 * 电话的具体实现
 */
public class PhoneImpl implements Phone {

	@Override
	public void dail() {
		System.out.println("电话操作：拨打电话");
	}

	@Override
	public void hangup() {
		System.out.println("电话操作：挂断电话");
	}

}
