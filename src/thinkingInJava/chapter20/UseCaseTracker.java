package thinkingInJava.chapter20;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xuye on 2017年8月2日
 * <p>
 * 一个简单的注解处理器，使用反射遍历指定的类的方法，找到注解并解析处理
 */
public class UseCaseTracker {
	public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
		// 循环遍历传入的类的所有方法
		for (Method m : cl.getDeclaredMethods()) {
			// 每个方法都使用getAnnotation方法提取指定的注解对象
			UseCase uc = m.getAnnotation(UseCase.class);
			// 如果在方法里找到了注解对象，就使用注解里的方法，提取注解的值
			if (uc != null) {
				System.out.println("Found Use Case: id: " + uc.id()
						+ " ,description: " + uc.description());
				// 将该注解里指定的id从id列表里移除，表示该用例已测试(这里是在模仿测试业务)
				// ps：这里需要移除Integer对象，如果移除uc.id()会数组下标越界，原因是传入int值表示移除指定index位置的值
				useCases.remove(new Integer(uc.id()));
			}
		}
		for (int i : useCases) {
			// 打印没检查到的用例
			System.out.println("waring: missing use case: " + i);
		}
	}

	public static void main(String[] args) {
		List<Integer> useCases = new ArrayList<Integer>();
		Collections.addAll(useCases, 47, 48, 49, 50);
		trackUseCases(useCases, PasswordUtils.class);
	}
}
