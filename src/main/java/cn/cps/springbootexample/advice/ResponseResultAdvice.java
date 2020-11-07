package cn.cps.springbootexample.advice;

import cn.cps.springbootexample.annotation.ResponseResult;
import cn.cps.springbootexample.core.ErrorResult;
import cn.cps.springbootexample.core.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Cai Peishen
 * @Date: 2020/11/7 17:33
 * @Description:
 **/
@Slf4j
@ControllerAdvice
public class ResponseResultAdvice implements ResponseBodyAdvice<Object> {

    // 标记名称
    private final String RESULT_FLAG = "RESULT_FLAG";

    // 判断请求是否包含了我们需要的注解，没有直接返回，不需要重写返回结果
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ServletRequestAttributes sar = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sar.getRequest();
        // 判断请求 是否有包装标记
        ResponseResult responseResultAnn= (ResponseResult) request.getAttribute(RESULT_FLAG);
        return responseResultAnn == null ? false : true;
    }

    // 需要重写的请求
    @Override
    public Object beforeBodyWrite(Object obj, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        log.info("进入 返回体 重写格式 处理中...");
        if(obj instanceof ErrorResult){
            ErrorResult errorResult = (ErrorResult) obj;
            return R.genFailResult(errorResult.getErrorMessage());
        }
        System.out.println(obj.toString());
        return R.genSuccessResult(obj);
    }

}
