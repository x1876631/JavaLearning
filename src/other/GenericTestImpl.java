package other;

import java.util.ArrayList;
import javaBase.jvm.SubClass;
import javaBase.jvm.SuperClass;

/**
 * Created by xuye on 2018年3月14日
 * <p>
 * 泛型继承与通配符学习，参考：http://blog.csdn.net/u011784767/article/details/54016774
 */
public class GenericTestImpl {
	public static void main(String[] args) {
		ArrayList<SuperClass> superList = new ArrayList<SuperClass>();
		ArrayList<? extends SuperClass> subList = new ArrayList<SubClass>();
		/**
		 * 如果subList是ArrayList<SubClass>则会报错<br/>
		 * 但如果规定了类型为<? extends SuperClass>以后，则可以将子类列表赋值给父类列表
		 */
		subList = superList;
		// superList = subList;
		System.out.println("now code is ok");
	}
}
