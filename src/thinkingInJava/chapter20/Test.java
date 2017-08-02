package thinkingInJava.chapter20;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xuye on 2017年8月2日
 * <p>
 * 学习如何自定义一个注解
 * 
 * @target 定义了该注解用在何处，有7种，比如：方法、域等
 * @retention 定义了该注解应该在什么级别保存该注解信息，有3种，比如：源代码、类文件还是运行时
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {

}
