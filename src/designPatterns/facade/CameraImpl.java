package designPatterns.facade;

/**
 * created by xuye on 2017年11月23日
 * 
 * 相机的具体实现
 */
public class CameraImpl implements Camera {

	@Override
	public void open() {
		System.out.println("相机操作：打开相机");
	}

	@Override
	public void takePicture() {
		System.out.println("相机操作：拍照");
	}

	@Override
	public void close() {
		System.out.println("相机操作：关闭相机");
	}

}
