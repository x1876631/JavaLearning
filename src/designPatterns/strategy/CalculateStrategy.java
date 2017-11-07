package designPatterns.strategy;

/**
 * created by xuye on 2017年11月7日
 * 
 * 使用策略模式重构，交通工具计费类
 * 
 * 先定义一个抽象的价格计算接口，然后让每一种交通方式的计费都有一个计算策略类，这些策略类都实现了该抽象接口
 */
public interface CalculateStrategy {
	int calculatePrice(int km);
}
