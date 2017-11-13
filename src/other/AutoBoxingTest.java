package other;

/**
 * Created by xuye on 2017年11月13日
 * <p>
 * 自动拆装箱值的比较，解惑
 * 
 * 讲解文章见：http://www.jianshu.com/p/0ce2279c5691
 */
public class AutoBoxingTest {

	public static void test() {
		int i = 40;
		int i0 = 40;
		Integer i1 = 40;
		Integer i2 = 40;
		Integer i3 = 0;
		Integer i4 = new Integer(40);
		Integer i5 = new Integer(40);
		Integer i6 = new Integer(0);
		Double d1 = 1.0;
		Double d2 = 1.0;

		// 值的比较，显然true
		System.out.println("i=i0\t\t" + (i == i0));

		// true， 因为i1创建时自动装箱，Integer类初始化后已经有了缓存，i2自动装箱后用的也是这个缓存里的同一个对象
		System.out.println("i1=i2\t\t" + (i1 == i2));

		// true，数字计算时，都自动拆箱成值比较，值一样就就是true
		System.out.println("i1=i2+i3\t" + (i1 == i2 + i3));

		// false，显示地创建了2个Integer对象，没用缓存，2个对象的地址不同，所以不是一个东西
		System.out.println("i4=i5\t\t" + (i4 == i5));

		// true，同样计算时拆箱成值40=40+0
		System.out.println("i4=i5+i6\t" + (i4 == i5 + i6));

		// false，double类型拆装箱不使用缓存，所以每次都是新的对象，自然不是一个
		System.out.println("d1=d2\t\t" + (d1 == d2));

		System.out.println();
	}

	public static void main(String[] args) {
		AutoBoxingTest.test();
	}

}
