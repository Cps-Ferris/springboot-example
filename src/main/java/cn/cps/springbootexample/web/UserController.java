package cn.cps.springbootexample.web;

import cn.cps.springbootexample.annotation.Token;
import cn.cps.springbootexample.core.R;
import cn.cps.springbootexample.entity.user.to.UserLoginTO;
import cn.cps.springbootexample.entity.user.to.UserInfoTO;
import cn.cps.springbootexample.entity.user.vo.UserInfoVO;
import cn.cps.springbootexample.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: Cai Peishen
 * @Date: 2020/6/29 12:04
 * @Description: 用户接口Controller
 */
@Slf4j
@Api(tags="用户接口API")
@RestController
@RequestMapping("/userTO")
public class UserController {

    @Resource
    private UserService userService;


    @PostMapping("/getUserById")
    @ApiOperation(value="根据ID查询用户信息 - BaseMapper自带方法")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", required = true, value = "用户ID"),
    })
    public Object getUserById(@RequestBody UserInfoTO userInfoTO){
        log.info("/getUserById，参数为{}", userInfoTO.toString());
        if(userInfoTO == null || userInfoTO.getId() == null){
            return R.genFailResult("请输入完整参数!");
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
            return R.genFailResult("请输入完整参数!");
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
    public Object userLogin(@RequestBody UserLoginTO userLoginTO){
        if(userLoginTO==null || userLoginTO.getPassword() == null || userLoginTO.getUsername() == null || "".equals(userLoginTO.getPassword()) || "".equals(userLoginTO.getUsername()) ){
            return R.genFailResult("请输入完整参数!");
        }
        UserInfoVO userInfoVO = userService.userLogin(userLoginTO);
        return R.genSuccessResult(userInfoVO);
    }


    /**
     * 验证Token并返回用户信息
     * @return
     */
    @Token
    @PostMapping("/getUserByToken")
    @ApiOperation(value="验证Token并返回用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", required = true, value = "Token")
    })
    public Object userLogin(){
        return null;
    }

}
