package cn.cps.springbootexample.web;

import cn.cps.springbootexample.core.R;
import cn.cps.springbootexample.entity.user.User;
import cn.cps.springbootexample.entity.user.to.UserTO;
import cn.cps.springbootexample.entity.user.vo.UserVO;
import cn.cps.springbootexample.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    @ApiOperation(value="根据ID查询用户 - 使用Mybatis-Plus BaseMapper自带方法")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", dataType = "int", required = true, value = "用户ID"),
    })
    public Object getUserById(@RequestBody UserTO userTO){
        log.info("/getUserById，参数为{}",userTO.toString());
        UserVO userVO = userService.getUserById(userTO);
        return R.genSuccessResult(userVO);
    }

    /**
     * 查询用户集合
     * 使用Mybatis-Plus分页 自定义SQL
     * @param userTO
     * @return
     */
    @PostMapping("/getUserList")
    @ApiOperation(value="查询用户集合 - 使用Mybatis-Plus分页 自定义SQL查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "current", dataType = "int", required = true, value = "当前页"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = true, value = "页容量")
    })
    public Object getUserList(@RequestBody UserTO userTO){
        log.info("/getUserList，参数为{}",userTO.toString());
        //分页查询
        Page<User> userPage = new Page<User>(userTO.getCurrent(), userTO.getPageSize());
        //按ID正序排列
        userPage.setAsc("id");
        //查询过的数据会封装在userPage中
        Page<UserVO> userVOPage = userService.getUserList(userPage, userTO);
        //返回数据
        return R.genSuccessResult(userPage);
    }


    /**
     * 根据姓名查询用户集合
     * 使用Mybatis-Plus带条件 分页查询
     * @param userTO
     * @return
     */
    @PostMapping("/getUserListByUserName")
    @ApiOperation(value="根据姓名查询用户集合 - 使用Mybatis-Plus分页 QueryWrapper带条件查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "current", dataType = "int", required = true, value = "当前页"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = true, value = "页容量"),
            @ApiImplicitParam(paramType = "query", name = "userName", dataType = "String", required = false, value = "用户名")
    })
    public Object getUserListByUserName(@RequestBody UserTO userTO){
        log.info("/getUserListByUserName，参数为{}",userTO.toString());
        //分页查询
        Page<User> userPage = new Page<User>(userTO.getCurrent(), userTO.getPageSize());
        //按ID正序排列
        userPage.setAsc("id");
        //查询过的数据会封装在userPage中
        userService.getUserListByUserName(userPage, userTO);
        //返回数据
        return R.genSuccessResult(userPage);
    }



}
