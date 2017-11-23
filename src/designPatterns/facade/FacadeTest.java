package designPatterns.facade;

/**
 * created by xuye on 2017年11月23日
 * 
 * 外观模式
 * 
 * 一般用于封装sdk库的api接口，将实现细节隐藏，只对外暴露希望用户统一调用的接口
 */
public class FacadeTest {
	public static void main(String[] args) {
		MobilePhone phone = new MobilePhone();
		phone.takePicture();
		phone.vedioChat();
	}
}
