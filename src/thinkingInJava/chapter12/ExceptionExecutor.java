package thinkingInJava.chapter12;

/**
 * Created by xuye on 2017-4-4
 * 
 * 异常执行者
 */
public class ExceptionExecutor {

	public static void f() throws MyException {
		throw new MyException("MyException is called!", 111);
	}

	public static void h() throws MyException {
		try {
			f();
		} catch (MyException e) {
			System.out.println("catch f()!");
			e.printStackTrace();
			// 重新抛出时，调用fillInStackTrace，将最新的调用信息加到原调用栈中
//			throw (MyException) e.fillInStackTrace();
			 throw e;
		}
	}

	/**
	 * 运行时异常编译时不会被检查，不需要try-catch
	 */
	public static void throwRuntimeException() {
		System.out.println("throwRuntimeException() is called!");
		throw new RuntimeException("runtime exception!");
	}

	public static void main(String[] args) {
		try {
			h();
		} catch (MyException e) {
			// printStackTrace打印了方法调用出到异常抛出处的方法调用序列，无参数版本表示输出到标准错误，可以选择其他输出流
			e.printStackTrace(System.out);
			throwRuntimeException();
			return;
		} finally {
			//finally一定会执行
			System.out.println("finally is called!");
		}
	}
}
