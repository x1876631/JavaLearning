package thinkingInJava.chapter14;

import java.util.HashMap;

/**
 * Created by xuye on 2017-4-9
 * 
 * 通用的类型计数器，用于学习isAssignableFrom
 *	
 */
public class TypeCounter extends HashMap<Class<?>, Integer> {
	private Class<?> mBaseType;//要统计的类型

	public TypeCounter(Class<?> baseType) {
		this.mBaseType = baseType;
	}

	public void count(Object obj) {
		Class<?> type = obj.getClass();
		// isAssignableFrom()方法检查归属(继承)结构，如果是同类型的则
		if (!mBaseType.isAssignableFrom(type)) {
			throw new RuntimeException(obj + " incorrect type: " + type
					+ " should be type or subtype of " + mBaseType);
		}
		countClass(type);
	}

	/**
	 * 统计当前类型(含父类)的计数值
	 * @param type
	 */
	private void countClass(Class<?> type) {
		//检查当前类型的计数值
		Integer quantity = get(type);
		//增加当前类型的计数
		put(type, quantity == null ? 1 : quantity + 1);
		
		//检查父类并计数
		Class<?> superClass = type.getSuperclass();
		if(superClass!=null && mBaseType.isAssignableFrom(superClass)){
			countClass(superClass);
		}
	}
}
