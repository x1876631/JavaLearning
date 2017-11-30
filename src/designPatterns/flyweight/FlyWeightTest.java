package designPatterns.flyweight;

/**
 * created by xuye on 2017年11月30日
 * 
 * 享元模式的测试类
 */
public class FlyWeightTest {

	private static final String FROM = "北京";
	private static final String TO = "上海";
	private static final String BUNK_TOP = "上铺";
	private static final String BUNK_BOTTOM = "下铺";

	public static void main(String[] args) {
		Ticket ticket1 = TicketFactory.getTicket(FROM, TO);
		ticket1.showTicketInfo(BUNK_TOP);

		Ticket ticket2 = TicketFactory.getTicket(FROM, TO);
		ticket2.showTicketInfo(BUNK_BOTTOM);
	}
}
