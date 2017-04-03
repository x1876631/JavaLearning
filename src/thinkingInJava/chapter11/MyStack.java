package thinkingInJava.chapter11;

import java.util.LinkedList;

/**
 * Created by xuye on 2017-4-3
 *	
 * @param <T>
 * 使用LinkedList实现的栈
 */
public class MyStack<T> {
	private LinkedList<T> storage = new LinkedList<T>();
	
	/**
	 * 入栈，添加一个元素到列表头部
	 * @param t
	 */
	public void push(T t){
		storage.addFirst(t);
	}
	
	/**
	 * 出栈，从列表头部移除一个元素，并返回该元素
	 * @return
	 */
	public T pop(){
		return storage.removeFirst();
	}
	
	/**
	 * 返回列表头部元素
	 * @return
	 */
	public T peek(){
		return storage.getFirst();
	}
	
	/**
	 * 检查栈是否为空
	 * @return
	 */
	public boolean empty(){
		return storage.isEmpty();
	}
	
	public String toString(){
		return storage.toString();
	}
}
