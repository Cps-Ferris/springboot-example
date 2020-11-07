package cn.cps.springbootexample.interceptor;

import cn.cps.springbootexample.annotation.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author: Cai Peishen
 * @Date: 2020/11/7 17:18
 * @Description: 请求拦截器
 **/
@Slf4j
public class ResultInterceptor implements HandlerInterceptor {

    // 标记名称
    private final String RESULT_FLAG = "RESULT_FLAG";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 只拦截controller进来的方法
        if(handler instanceof HandlerMethod){
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            // 判断是否在类对象上面加了注解
            if(clazz.isAnnotationPresent(ResponseResult.class)){
                // 设置此请求返回体，需要包装，往下传递,在ResponseBodyAdvice接口进行判断
                request.setAttribute(RESULT_FLAG,clazz.getAnnotation(ResponseResult.class));
            }else if(method.isAnnotationPresent(ResponseResult.class)){
                // 设置此请求返回体，需要包装,往下传递,在ResponseBodyAdvice接口进行判断
                request.setAttribute(RESULT_FLAG,method.getAnnotation(ResponseResult.class));
            }
        }

        log.info("----- ResultInterceptor Pass！-----");
        return true;
    }

}
