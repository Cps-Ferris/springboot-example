package cn.cps.springbootexample.web;

import cn.cps.springbootexample.core.R;
import cn.cps.springbootexample.entity.user.User;
import cn.cps.springbootexample.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    //实战中 会有专门存放常量、枚举类型的类
    public enum GenderCode{

        WO_MAN(0,"女"),//女
        MAN(1,"男");//男

        int code;
        String value;

        GenderCode(int code,String value){
            this.code = code;
            this.value = value;
        }

        public int getCode(){
            return this.code;
        }
        public String getValue(){
            return this.value;
        }
    }


    @PostMapping("/getUserById")
    @ApiOperation(value="根据ID查询用户 - 使用Mybatis-Plus BaseMapper自带方法")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", dataType = "int", required = true, value = "用户ID"),
    })
    public Object getUserById(@RequestBody User user){
        log.info("/getUserById，参数为{}",user.toString());
        User u = userService.getUserById(user);
        return R.genSuccessResult(u);
    }

    /**
     * 查询用户集合
     * 使用Mybatis-Plus分页 自定义SQL
     * @param user
     * @return
     */
    @PostMapping("/getUserList")
    @ApiOperation(value="查询用户集合 - 使用Mybatis-Plus分页 自定义SQL查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "current", dataType = "int", required = true, value = "当前页"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = true, value = "页容量")
    })
    public Object getUserList(@RequestBody User user){
        log.info("/getUserList，参数为{}",user.toString());
        //分页查询
        Page<User> userPage = new Page<User>(user.getCurrent(), user.getPageSize());
        //按ID正序排列
        userPage.setAsc("id");
        //查询过的数据会封装在userPage中
        userService.getUserList(userPage, user);
        //更新用户的性别(中文)
        setGenderName(userPage.getRecords());
        //返回数据
        return R.genSuccessResult(userPage);
    }


    /**
     * 根据姓名查询用户集合
     * 使用Mybatis-Plus带条件 分页查询
     * @param user
     * @return
     */
    @PostMapping("/getUserListByUserName")
    @ApiOperation(value="根据姓名查询用户集合 - 使用Mybatis-Plus分页 QueryWrapper带条件查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "current", dataType = "int", required = true, value = "当前页"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", required = true, value = "页容量"),
            @ApiImplicitParam(paramType = "query", name = "userName", dataType = "String", required = false, value = "用户名")
    })
    public Object getUserListByUserName(@RequestBody User user){
        log.info("/getUserListByUserName，参数为{}",user.toString());
        //分页查询
        Page<User> userPage = new Page<User>(user.getCurrent(), user.getPageSize());
        //按ID正序排列
        userPage.setAsc("id");
        //查询过的数据会封装在userPage中
        userService.getUserListByUserName(userPage, user);
        //更新用户的性别(中文)
        setGenderName(userPage.getRecords());
        //返回数据
        return R.genSuccessResult(userPage);
    }


    /**
     * 设置性别(中文)
     * @param userList
     */
    public void setGenderName(List<User> userList){
        for(User vo : userList){
            if(vo.getGender()== GenderCode.WO_MAN.getCode()){
                vo.setGenderName(GenderCode.WO_MAN.getValue());
            }else if(vo.getGender()==GenderCode.MAN.getCode()){
                vo.setGenderName(GenderCode.MAN.getValue());
            }else{
                vo.setGenderName("其他");
            }
        }
    }

}
