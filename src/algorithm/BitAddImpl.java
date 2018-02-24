package algorithm;

/**
 * Created by xuye on 2018年2月24日
 * <p>
 * 不用加减乘除实现加法操作<br/>
 * 原理参考：http://blog.csdn.net/gatieme/article/details/51493414
 */
public class BitAddImpl {
	public static int add(int x, int y) {
		int sum;
		int carry;
		do {
			// 异或(相同值才置0，不同置1)的结果是相当于没有进位的加法
			sum = x ^ y;
			// 然后计算进位，位与运算规则是全1才是1，可以表示进位，那么计算出的进位怎么用呢？ 让carry值全都进1位，即<<1
			carry = (x & y) << 1;
			x = sum;
			y = carry;
			// 只要有进位就继续计算，直到没有进位为止
		} while (y != 0);
		return x;
	}

	public static void main(String[] args) {
		System.out.println("1+3=" + add(1, 3));
	}
}
