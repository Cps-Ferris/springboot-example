package cn.cps.springbootexample.dao;

import cn.cps.springbootexample.entity.user.User;
import cn.cps.springbootexample.entity.user.to.UserTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Author: Cai Peishen
 * @Date: 2020/6/29 11:59
 * @Description: 用户Mapper接口 继承 MyBatis-Plus 的BaseMapper接口
 */
public interface UserMapper extends BaseMapper<User> {

    Page<User> getUserList(Page<User> userPage, UserTO userTO);

}
