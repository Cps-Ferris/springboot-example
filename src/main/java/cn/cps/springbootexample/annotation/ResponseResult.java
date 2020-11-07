package cn.cps.springbootexample.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * @Author: Cai Peishen
 * @Date: 2020/11/7 17:15
 * @Description:
 **/
@Documented
@Target({ TYPE, METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseResult {



}
