package cn.cps.springbootexample.web;

import cn.cps.springbootexample.annotation.Token;
import cn.cps.springbootexample.context.UserContext;
import cn.cps.springbootexample.core.R;
import cn.cps.springbootexample.core.ResultCode;
import cn.cps.springbootexample.entity.user.to.UserLoginTO;
import cn.cps.springbootexample.entity.user.to.UserInfoTO;
import cn.cps.springbootexample.entity.user.vo.UserInfoVO;
import cn.cps.springbootexample.service.UserService;

import cn.cps.springbootexample.utils.TokenUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Cai Peishen
 * @Date: 2020/6/29 12:04
 * @Description: 用户接口Controller
 */
@Slf4j
@Api(tags="用户接口API")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/getUserById")
    @ApiOperation(value="根据ID查询用户信息 - BaseMapper自带方法")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", required = true, value = "用户ID"),
    })
    public Object getUserById(@RequestBody UserInfoTO userInfoTO){
        log.info("/getUserById，参数为{}", userInfoTO.toString());
        if(userInfoTO == null || userInfoTO.getId() == null){
            return R.genFailResult("请输入完整参数...");
        };
        UserInfoVO userInfoVO = userService.getUserById(userInfoTO);
        return R.genSuccessResult(userInfoVO);
    }


    /**
     * 查询用户集合
     * 使用Mybatis-Plus分页 自定义SQL
     * @param userInfoTO
     * @return
     */
    @PostMapping("/getUserList")
    @ApiOperation(value="查询每个用户、角色 信息 - 自定义SQL查询并分页")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "current",  required = true, value = "当前页"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", required = true, value = "页容量"),
            @ApiImplicitParam(paramType = "query", name = "username", required = false, value = "用户名")
    })
    public Object getUserList(@RequestBody UserInfoTO userInfoTO){
        log.info("/getUserList，参数为{}", userInfoTO.toString());
        if(userInfoTO == null || userInfoTO.getCurrent() == null || userInfoTO.getPageSize() == null){
            return R.genFailResult("请输入完整参数...");
        };
        IPage<UserInfoVO> userInfoVOIPage = userService.getUserList(userInfoTO);
        return R.genSuccessResult(userInfoVOIPage);
    }



    /**
     * 用户登录同时返回Token
     * @param userLoginTO
     * @return
     */
    @PostMapping("/userLogin")
    @ApiOperation(value="用户登录同时返回Token - QueryWrapper定义查询条件")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", required = true, value = "用户名"),
            @ApiImplicitParam(paramType = "query", name = "password", required = true, value = "用户密码")
    })
    public Object userLogin(@RequestBody UserLoginTO userLoginTO) throws JsonProcessingException {
        log.info("/userLogin，参数为{}", userLoginTO.toString());

        if(userLoginTO==null || userLoginTO.getPassword() == null || userLoginTO.getUsername() == null || "".equals(userLoginTO.getPassword()) || "".equals(userLoginTO.getUsername()) ){
            return R.genFailResult("请输入完整参数...");
        }

        UserInfoVO userInfoVO = userService.userLogin(userLoginTO);

        if(userInfoVO==null){
            return R.genFailResult("用户名或密码错误...");
        }

        //对数据进行加密 当作Token
        String token = TokenUtils.getToken(userInfoVO.getUsername());

        log.info("token生成成功：{}",token);

        //jackson 处理成JSON格式 类似 FASTJSON
        ObjectMapper objectMapper = new ObjectMapper();
        String userInfoVOJson = objectMapper.writeValueAsString(userInfoVO);

        //Token 暂时存在session中 后面会存在redis中
        //request.getSession().setAttribute(Token,userInfoVOJson);

        //Token 存在redis中 并设置有效时间
        stringRedisTemplate.opsForValue().set(token,userInfoVOJson,60, TimeUnit.SECONDS);

        return R.genSuccessResult(token);
    }


    /**
     * 验证Token并返回用户信息
     * @return
     */
    @Token
    @PostMapping("/getUserByToken")
    @ApiOperation(value="验证Token并返回用户信息")
    @ApiImplicitParam(paramType = "header", name = "token", required = true, value = "Token")
    public Object getUserByToken(){
        UserInfoVO userInfoVO = UserContext.getUserInfoVO();
        return R.genSuccessResult(userInfoVO);
    }



    /**
     * token校验失败跳转接口
     * @param request
     * @return
     */
    @ApiOperation(value="token校验失败跳转接口")
    @PostMapping("/returnLogin")
    public Object returnLogin(HttpServletRequest request) {
        String token_error = (String) request.getAttribute("token_error");
        log.error("tokenIptor校验失败跳转接口.{}",token_error);
        return R.genFailResult(token_error);
    }


}
