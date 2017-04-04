package thinkingInJava.chapter12;

public class StormInning extends Inning implements Storm {

	public StormInning() throws BException {
	}

	@Override
	public void rainHard() throws B1Exception {

	}

	/*
	 * 重写方法可以少异常，但是不能多异常 这里比基类方法少了个A1Exception
	 */
	@Override
	public void atBat() throws A2Exception {

	}

	// 子类重写的方法不允许比基类方法多添加新异常
	// public void walk() throws AException{
	//
	// }

	@Override
	public void event() throws B1Exception {

	}

	public static void main(String[] args) {
		try {
			Inning inning = new StormInning();
			inning.atBat();
		} catch (AException e) {
			e.printStackTrace();
		} catch (BException e) {
			e.printStackTrace();
		}
	}

}
