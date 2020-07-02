package cn.cps.springbootexample.entity.user.vo;

import cn.cps.springbootexample.entity.role.Role;
import cn.cps.springbootexample.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author _Cps
 * @create 2019-02-14 10:12
 * @Description: 用户VO实体类(后台到前端)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO extends User {

    private Integer roleId;

    private String roleName;

    private String genderName;

}
