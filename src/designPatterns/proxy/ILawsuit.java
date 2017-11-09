package designPatterns.proxy;

/**
 * created by xuye on 2017年11月9日
 * 
 * 代理模式
 * 
 * 代理的动作需要保持一致，所以定义一个抽象接口
 * 
 * 这里以一个诉讼过程做例子
 */
public interface ILawsuit {
	void submit();// 提交申请

	void burden();// 进行举证

	void defend();// 开始辩护

	void finished();// 诉讼完成
}
