package designPatterns.flyweight;

/**
 * created by xuye on 2017年11月30日
 * 
 * 享元模式的抽象接口，这里以车票为例
 */
public interface Ticket {

	/**
	 * 查询车票信息
	 */
	void showTicketInfo(String bunk);
}
