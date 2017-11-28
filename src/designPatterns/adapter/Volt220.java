package designPatterns.adapter;

/**
 * created by xuye on 2017年11月28日
 * 
 * 适配器设计模式中，被转换的对象<br/>
 * 这里以一个220v电压为例，这个类只能提供220v电压，但是现在用户需要使用这个类提供5v电压，而且不能修改这个类
 */
public class Volt220 {

	public int getVolt220() {
		return 220;
	}
}
