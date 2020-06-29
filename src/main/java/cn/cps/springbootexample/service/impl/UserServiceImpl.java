package cn.cps.springbootexample.service.impl;

import cn.cps.springbootexample.dao.UserMapper;
import cn.cps.springbootexample.entity.User;
import cn.cps.springbootexample.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @author _Cps
 * @create 2019-02-14 10:25
 * @Description: 用户Service实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    public UserMapper userMapper;


    @Override
    public User getUserById(User user) {
        return userMapper.selectById(user);
    }

    @Override
    public void getUserListByUserName(Page<User> userPage, User user) {
        //使用QueryWrapper定义查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.like("username", user.getUserName());
        userMapper.selectPage(userPage,queryWrapper);
    }

    @Override
    public void getUserList(Page<User> userPage, User userDTO) {
        userMapper.getUserList(userPage, userDTO);
    }

}
