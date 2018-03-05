package other;

/**
 * Created by xuye on 2018年3月5日
 * <p>
 * 内外类private变量相互访问测试类
 */
public class OutterClassTestImpl {

	public static void main(String[] args) {
		OutterClass outterClass = new OutterClass();
		outterClass.getInnerField();

		OutterClass.InnerClass innerClass = outterClass.new InnerClass();
		innerClass.getOutField();
	}
}
