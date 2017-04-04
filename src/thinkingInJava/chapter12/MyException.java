package thinkingInJava.chapter12;

/**
 * Created by xuye on 2017-4-4
 *
 * 自定义异常
 */
public class MyException extends Exception {
	private int value;

	public MyException() {

	}

	public MyException(String msg) {
		super(msg);
	}

	public MyException(String msg, int value) {
		super(msg);
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	/*
	 * 打印异常信息，相当于toString
	 */
	@Override
	public String getMessage() {
		return "Detail Message :" + value + " " + super.getMessage();
	}
}
