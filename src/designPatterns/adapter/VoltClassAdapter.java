package designPatterns.adapter;

/**
 * created by xuye on 2017年11月28日
 * 
 * 类适配器设计模式中的适配器角色<br/>
 * 这里以一个将220v电压转成5v电压的例子说明<br/>
 * 
 * 类适配：某个类不支持所需接口，对该类做适配，所以adapter一般会继续要适配的类<br/>
 * 类适配有些缺点，比如由于继承会暴露父类方法，多出来很多无用方法等<br/>
 * 【实现方式】就是适配器继承要转换的类，然后实现期待的接口
 */
public class VoltClassAdapter extends Volt220 implements FiveVolt {

	@Override
	public int getVolt5() {
		// 这里通常是要做比较复杂的转换的，这里为了简单就直接返回个5
		return 5;
	}

}
