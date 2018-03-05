package other;

/**
 * Created by xuye on 2018年3月5日
 * <p>
 * 一个普通的类，里面包含一个内部类InnerClass，用于测试内外类private变量相互访问<br/>
 * 结论是可以互相调用对方的的private，原理见：https://www.zhihu.com/question/54730071<br/>
 * 
 * 1、outter调用inner的prvate原理简介：<br/>
 * 正常来说嵌套在一起的2个类，编译以后还是分成2个class文件的，他们相当于是同级的，不可以互相访问private和protected级的资源<br/>
 * 但是java语言规范里已经标明可以互相访问了，所以jvm里用了另外的方法实现了访问。<br/>
 * 原理就是在innerclass里生成一个静态方法，相当于用非private的方式去调用private，当然就没问题了。<br/>
 * 
 * 2、inner调用outter的prvate原理简介：<br/>
 * 和1一样，在外部类OutterClass里生成了一个private变量对应的static方法，让内部类去调用外部类的这个static方法
 * 
 */
public class OutterClass {
	private String mOutterField = "outter_field";

	public String getInnerField() {
		InnerClass innerClass = new InnerClass();
		String field = innerClass.mInnerField;
		System.out.println("Out call Inner private field：" + field);
		return field;
	}

	class InnerClass {

		private String mInnerField = "inner_field";

		public String getOutField() {
			System.out.println("Inner call Out private field：" + mOutterField);
			return mOutterField;
		}
	}
}
