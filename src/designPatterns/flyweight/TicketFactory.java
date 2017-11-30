package designPatterns.flyweight;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * created by xuye on 2017年11月30日
 * 
 * 享元模式的工厂类，这里以一个火车票查询类为例
 * 
 * 实现方式：内部持有一个map，将对象不变的信息作为key，变化的是对象其他信息。所以将对象根据key存起来，每次先从缓存取对象，没有再创建
 */
public class TicketFactory {

	static Map<String, Ticket> sTicketMap = new ConcurrentHashMap<>();

	public static Ticket getTicket(String from, String to) {
		// return new TrainTicket(from, to);//每次直接创建对象，很耗cpu和内存

		String key = from + "-" + to;
		if (sTicketMap.containsKey(key)) {
			System.out.println("享元模式，使用缓存：" + key);
			return sTicketMap.get(key);
		} else {
			System.out.println("享元模式，创建对象：" + key);
			Ticket ticket = new TrainTicket(from, to);
			sTicketMap.put(key, ticket);
			return ticket;
		}

	}
}
