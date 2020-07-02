package cn.cps.springbootexample.web;

import cn.cps.springbootexample.core.R;
import cn.cps.springbootexample.entity.user.User;
import cn.cps.springbootexample.entity.user.to.UserTO;
import cn.cps.springbootexample.entity.user.vo.UserVO;
import cn.cps.springbootexample.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: Cai Peishen
 * @Date: 2020/6/29 12:04
 * @Description: 用户接口Controller
 */
@Slf4j
@Api(tags="用户接口API",value = "测试并使用swaggerAPI文档")
@RestController
@RequestMapping("/userTO")
public class UserController {

    @Resource
    private UserService userService;


    @PostMapping("/getUserById")
    @ApiOperation(value="根据ID查询用户信息 - BaseMapper自带方法 or QueryWrapper定义查询条件")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", dataType = "int", required = true, value = "用户ID"),
    })
    public Object getUserById(@RequestBody UserTO userTO){
        log.info("/getUserById，参数为{}",userTO.toString());
        if(userTO==null || userTO.getId()==null){
            return R.genFailResult("请输入完整参数!");
        };
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
    @ApiOperation(value="查询每个用户的角色信息 - 自定义SQL查询并分页")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "current", dataType = "int", required = true, value = "当前页"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = true, value = "页容量"),
            @ApiImplicitParam(paramType = "query", name = "username", dataType = "String", required = false, value = "用户名")
    })
    public Object getUserList(@RequestBody UserTO userTO){
        log.info("/getUserList，参数为{}",userTO.toString());
        if(userTO==null || userTO.getCurrent()==null || userTO.getPageSize() == null){
            return R.genFailResult("请输入完整参数!");
        };
        IPage<UserVO> userVOPage = userService.getUserList(userTO);
        return R.genSuccessResult(userVOPage);
    }

}
