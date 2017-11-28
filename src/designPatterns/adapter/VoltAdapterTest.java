package designPatterns.adapter;

/**
 * created by xuye on 2017年11月28日
 * 
 * 类适配器模式的测试类
 */
public class VoltAdapterTest {
	public static void main(String[] args) {

		VoltClassAdapter classAdapter = new VoltClassAdapter();
		System.out.println("【类适配】转换后的输出电压: " + classAdapter.getVolt5());

		VoltObjectAdapter objectAdapter = new VoltObjectAdapter(new Volt220());
		System.out.println("【对象适配】转换后的输出电压: " + objectAdapter.getVolt5());
	}
}
