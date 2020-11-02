package cn.cps.springbootexample.annotation;

import java.lang.annotation.*;

/**
 * @Author: Cai Peishen
 * @Date: 2020/11/2 17:32
 * @Description: 统一收集controller日志
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WebLog {

    /**
     * 日志描述信息
     * @return
     */
    String description() default "";

}
