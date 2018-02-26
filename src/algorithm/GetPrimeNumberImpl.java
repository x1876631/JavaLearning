package algorithm;

/**
 * Created by xuye on 2018年2月26日
 * <p>
 * 求1~100以内的质数<br/>
 * 参考：http://blog.csdn.net/wu_kung/article/details/21798339
 */
public class GetPrimeNumberImpl {

	/**
	 * 最简单的解法，效率最低，每次判断都要从2到i取模试一下
	 */
	public static void GetPrimeNumberSimple() {
		System.out.println("简单方法求质数");
		for (int i = 2; i <= 100; i++) {
			boolean flag = true;
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					flag = false;
					break;
				}
			}
			if (flag) {
				System.out.print(i + " ");
			}
		}
		System.out.println();
	}

	public static void GetPrimeNumberNormal() {
		System.out.println("正常方法求质数");
		for (int i = 2; i <= 100; i++) {
			boolean flag = true;
			for (int j = 2; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					flag = false;
					break;
				}
			}
			if (flag) {
				System.out.print(i + " ");
			}
		}
		System.out.println("");
	}

	/**
	 * 利用筛选法求100以内的素数<br/>
	 * (所谓的筛选法：是指从小到大筛去已知的一个素数的所有倍数，根据2我们可以筛去“4,6,8,...,100”等数,
	 * 然后根据3可以筛去“9,15,...,99”等数，注意此时的6,12早就被作为2的倍数给筛去了，由于4已经被筛去了 ，所以下一个筛选数是5)
	 * 编程原理： 定义一个大小为101的数组,把被筛去的数赋值为1,留下未被筛去的并且数组下标大于等于2的数输出，输出的就是质数。
	 */
	public static void GetPrimeNumberBest() {
		System.out.println("筛选方法求质数");
		int[] a = new int[101];
		int i, j = 2;

		while (j < 101) {
			if (a[j] == 0) {
				for (i = j + 1; i < 101; i++) {
					if (i % j == 0) {
						a[i] = 1;
					}
				}
			}
			j++;
		}

		for (int k = 0; k < 101; k++) {
			if (k >= 2 && a[k] == 0) {
				System.out.print(k + " ");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		GetPrimeNumberSimple();
		GetPrimeNumberNormal();
		GetPrimeNumberBest();
	}
}
