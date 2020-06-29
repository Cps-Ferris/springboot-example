package cn.cps.springbootexample.service;

import cn.cps.springbootexample.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author _Cps
 * @create 2019-02-14 10:25
 * @Description: 用户Service
 */
public interface UserService{

    User getUserById(User user);

    void getUserListByUserName(Page<User> userPage, User userDTO);

    void getUserList(Page<User> userPage, User userDTO);

}
