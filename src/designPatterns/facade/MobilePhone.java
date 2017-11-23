package designPatterns.facade;

/**
 * created by xuye on 2017年11月23日
 * 
 * 外观模式
 * 
 * 和代理模式很像，但是代理模式要求代理者和被代理者实现同样的接口动作，代理者在同名动作里使用被代理者的动作。
 * 
 * 而外观模式更侧重表达的意思是：隐藏实现细节，暴露统一接口。其内部动作不仅仅是执行子系统的同名动作，比如takePicture()方法
 * 
 * 这里以一个手机为例，手机隐藏了内部电话、相机子系统，只对外暴露相关调用接口
 */
public class MobilePhone {
	private Phone mPhone;
	private Camera mCamera;

	public MobilePhone() {
		mPhone = new PhoneImpl();
		mCamera = new CameraImpl();
	}

	public void dail() {
		mPhone.dail();
	}

	public void vedioChat() {
		System.out.println("\n----使用手机：发起视频聊天----");
		mCamera.open();
		mPhone.dail();
	}

	public void hangup() {
		mPhone.hangup();
	}

	public void takePicture() {
		// MobilePhone这里不算是代理者，因为其没实现Camera接口，也不仅仅是调用mCamera实例的同名动作
		System.out.println("\n----使用手机：开始拍照----");
		mCamera.open();
		mCamera.takePicture();
	}

	public void closeCamera() {
		mCamera.close();
	}
}
