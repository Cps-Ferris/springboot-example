package cn.cps.springbootexample.service.impl;

import cn.cps.springbootexample.dao.UserMapper;
import cn.cps.springbootexample.entity.GlobalConstants;
import cn.cps.springbootexample.entity.user.User;
import cn.cps.springbootexample.entity.user.to.UserTO;
import cn.cps.springbootexample.entity.user.vo.UserVO;
import cn.cps.springbootexample.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

        //使用Mybatis-Plus自带方法
        User user = userMapper.selectById(userTO);
        //使用QueryWrapper定义查询条件
//        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//        queryWrapper.like("id", userTO.getId());
//        User user = userMapper.selectOne(queryWrapper);

        //将user的数据复制一份到userVO中
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);

        //更新用户的性别(中文)
        setGenderName(Arrays.asList(userVO));

        return userVO;
    }


    @Override
    public IPage<UserVO> getUserList(UserTO userTO) {
        //组装分页信息
        Page<UserVO> userVOPage = new Page<UserVO>(userTO.getCurrent(), userTO.getPageSize());
        userVOPage.setAsc("id");
        //调用查询方法
        IPage<UserVO> userVOIPage = userMapper.getUserList(userVOPage,userTO);
        //设置性别中文
        setGenderName(userVOIPage.getRecords());
        return userVOIPage;
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
