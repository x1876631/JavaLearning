package designPatterns.adapter;

/**
 * created by xuye on 2017年11月28日
 * 
 * 对象适配器模式<br/>
 * 对象适配：adapter仅实现期待接口，不继承待转换类，使用代理去操作转换类
 */
public class VoltObjectAdapter implements FiveVolt {
	private Volt220 mVolt220;

	public VoltObjectAdapter(Volt220 volt220) {
		this.mVolt220 = volt220;
	}

	@Override
	public int getVolt5() {
		return 5;
	}

	public int getVolt220() {
		return mVolt220.getVolt220();
	}

}
