package designPatterns.observer;

import java.util.Observable;

/**
 * created by xuye on 2017年11月21日
 * 
 * 具体的被观察者，这里以一个技术网站为例
 * 
 * 被观察者里有个集合，持有所有观察者，当自身有更新时，遍历更新持有的观察者
 */
public class DevTechFrontier extends Observable {

	/**
	 * 自定义的，更新自身的一个操作
	 * 
	 * @param content
	 *            更新的内容
	 */
	public void postNewPublication(String content) {
		setChanged();// 标识自身有更新，就是修改了changed标识
		notifyObservers(content);// 更新自身持有的所有观察者，就是遍历集合列表，挨个执行观察者的update方法
	}
}
