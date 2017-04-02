package thinkingInJava.chapter10;

public interface Selector {
	
	boolean end();//检查序列是否到末尾了

	Object current();//访问当前对象

	void next();//移到序列的下一个对象
}
