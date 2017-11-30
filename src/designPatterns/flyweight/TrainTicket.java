package designPatterns.flyweight;

import java.util.Random;

/**
 * created by xuye on 2017年11月30日
 * 
 * 火车票
 */
public class TrainTicket implements Ticket {

	private String mFrom;// 出发地
	private String mTo;// 目的地
	private String mBunk;// 铺位
	private int mPrice;// 价格

	public TrainTicket(String from, String to) {
		this.mFrom = from;
		this.mTo = to;
	}

	@Override
	public void showTicketInfo(String bunk) {
		mPrice = new Random().nextInt(300);
		System.out.println("购买 从" + mFrom + "到" + mTo + "的火车票，铺位：" + bunk + "，价格：" + mPrice);
	}

}
