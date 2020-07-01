package cn.cps.springbootexample.service.impl;

import cn.cps.springbootexample.dao.UserMapper;
import cn.cps.springbootexample.entity.GlobalConstants;
import cn.cps.springbootexample.entity.user.User;
import cn.cps.springbootexample.entity.user.to.UserTO;
import cn.cps.springbootexample.entity.user.vo.UserVO;
import cn.cps.springbootexample.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

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
    public UserVO getUserById(UserTO userTO) {
        User user = userMapper.selectById(userTO);
        UserVO userVO = new UserVO();
        //将user的数据复制一份到userVO中
        BeanUtils.copyProperties(user,userVO);
        //更新用户的性别(中文)
        setGenderName(Arrays.asList(userVO));
        return userVO;
    }


    @Override
    public Page<UserVO> getUserList(Page<User> userPage, UserTO userDTO) {
        userMapper.getUserList(userPage, userDTO);
        Page<UserVO> userVOPage = new Page<>(userPage.getCurrent(),userPage.getSize());
        BeanUtils.copyProperties(userPage,userVOPage);
        return userVOPage;
    }


    @Override
    public Page<UserVO> getUserListByUserName(Page<User> userPage, UserTO userTO) {
        //使用QueryWrapper定义查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.like("username", userTO.getUserName());
        userMapper.selectPage(userPage,queryWrapper);
        Page<UserVO> userVOPage = new Page<>(userPage.getCurrent(),userPage.getSize());
        BeanUtils.copyProperties(userPage,userVOPage);
        return userVOPage;
    }


    /**
     * 设置性别(中文)
     * @param userList
     */
    public void setGenderName(List<UserVO> userList){
        for(UserVO vo : userList){
            if(vo.getGender()== GlobalConstants.GenderCode.WO_MAN.getCode()){
                vo.setGenderName(GlobalConstants.GenderCode.WO_MAN.getValue());
            }else if(vo.getGender()== GlobalConstants.GenderCode.MAN.getCode()){
                vo.setGenderName(GlobalConstants.GenderCode.MAN.getValue());
            }else{
                vo.setGenderName("其他");
            }
        }
    }

}
