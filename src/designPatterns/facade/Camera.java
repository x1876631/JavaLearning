package designPatterns.facade;

/**
 * created by xuye on 2017年11月23日
 * 
 * 照相机的抽象接口
 */
public interface Camera {

	// 打开相机
	void open();

	// 照相
	void takePicture();

	// 关闭相机
	void close();
}
