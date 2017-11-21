package designPatterns.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * created by xuye on 2017年11月21日
 * 
 * 具体的观察者，当其观察的对象有更新时，自身能收到更新并做对应处理，适合一对多的关联系统
 * 
 * 这里以一个程序员为例，当程序员关注的网站更新时，通知该程序员
 */
public class Coder implements Observer {

	public String mName;

	public Coder(String name) {
		mName = name;
	}

	/**
	 * 被观察者更新时，调用观察者的该方法
	 * 
	 * @param o
	 *            被观察者
	 * @param arg
	 *            更新内容
	 */
	@Override
	public void update(Observable o, Object arg) {
		System.out.println(mName + "收到了 DevTechFrontier的更新，更新内容：" + arg);
	}

	@Override
	public String toString() {
		return "coder name:" + mName;
	}
}
