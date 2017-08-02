package thinkingInJava.chapter20;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xuye on 2017年8月2日
 * <p>
 * 带内容的注解，内容的定义类似方法定义
 * <P>
 * 编译器会对注解内定义的内容进行类型检查，description元素设置了默认值
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {
	public int id();

	public String description() default "no description";
}
