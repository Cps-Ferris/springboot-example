package cn.cps.springbootexample.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Cai Peishen
 * @Date: 2020/7/1 18:51
 * @Description: 自定义关于Token注解
 */
@Target(value = { ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Token {

    /** 登陆token是否校验  */
    boolean isToken() default true;

}
