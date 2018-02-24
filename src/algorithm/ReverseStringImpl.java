package algorithm;

/**
 * Created by xuye on 2018年2月24日
 * <p>
 * 反转字符串，输入一个字符串，输出它的倒序字符串，注意不能使用系统提供的API<br/>
 * 参考：https://www.cnblogs.com/JohnTsai/p/5606719.html
 */
public class ReverseStringImpl {

	private static final String CONTENT = "helalo";

	/**
	 * 拆成字符数组，倒序拼接
	 */
	public static String reverse1(String input) {
		char[] c = input.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = c.length - 1; i >= 0; i--) {
			sb.append(c[i]);
		}
		return sb.toString();
	}

	/**
	 * 拆成字符数组，首尾交换
	 */
	public static String reverse2(String input) {
		char[] cArray = input.toCharArray();
		int lastIndex = cArray.length - 1;
		for (int i = 0; i < cArray.length / 2; i++) {
			char c = cArray[i];
			cArray[i] = cArray[lastIndex - i];
			cArray[lastIndex - i] = c;
		}
		return new String(cArray);
	}

	/**
	 * 拆成字符数组，异或交换，空间复杂度小，因为不需要额外的空间。<br/>
	 * 原理是 B= B^A^B = A^(B^B) = A^(0) = A，完成了一次交换
	 * 第一次操作，A=A^B--->第二次操作：B=A^B(此时由于A=A^B，所以B=A^B=A^A^B=0^A=A)--->
	 * 第三次操作：A=A^B(B经过2次操作，此时值为原本A值，A现在值为原本A^原本B，所以本次操作A=A^B相当于A^B^A=B^0=B),
	 * 所以只要执行3次A^B就能完成A与B值的交换
	 */
	public static String reverse3(String input) {
		char[] cArray = input.toCharArray();
		int end = cArray.length - 1;
		for (int begin = 0; begin < end; begin++, end--) {
			// 注意：begin和end相等时，不要再异或计算了，否则会把那个值吞了
			cArray[begin] = (char) (cArray[begin] ^ cArray[end]);
			cArray[end] = (char) (cArray[begin] ^ cArray[end]);
			cArray[begin] = (char) (cArray[begin] ^ cArray[end]);
			// System.out
			// .println("第" + begin + "次交换，此时字符串值：" + new String(cArray));
		}
		return new String(cArray);
	}

	public static void main(String[] args) {
		System.out.println("倒序拼接：" + reverse1(CONTENT));
		System.out.println("数组交换：" + reverse2(CONTENT));
		System.out.println("异或计算：" + reverse3(CONTENT));
	}

}
