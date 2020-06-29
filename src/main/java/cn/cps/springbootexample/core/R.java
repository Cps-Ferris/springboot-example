package cn.cps.springbootexample.core;

/**
 * 响应结果
 */
public class R {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    //成功
    public static Result genSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }
    //成功
    public static Result genSuccessResult(String message) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(message);
    }
    //失败
    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }

    //携带数据
    public static Result genSuccessResult(Object data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }
    //自定义返回状态码
    public static Result genFailResult(ResultCode resultCode, String message) {
        return new Result()
                .setCode(resultCode)
                .setMessage(message);
    }
}
