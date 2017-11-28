package designPatterns.adapter;

/**
 * created by xuye on 2017年11月28日
 * 
 * 适配器设计模式中，用户期待的接口，通常提供用户希望使用的方法<br/>
 * 这里以一个5v电压转换为例
 */
public interface FiveVolt {
	int getVolt5();// 用户期待的是5v电压
}
