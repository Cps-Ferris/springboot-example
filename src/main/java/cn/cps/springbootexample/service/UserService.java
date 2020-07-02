package cn.cps.springbootexample.service;

import cn.cps.springbootexample.entity.user.to.UserTO;
import cn.cps.springbootexample.entity.user.vo.UserVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @author _Cps
 * @create 2019-02-14 10:25
 * @Description: 用户Service
 */
public interface UserService{

    UserVO getUserById(UserTO userDTO);

    IPage<UserVO> getUserList(UserTO userDTO);

}
