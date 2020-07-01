package cn.cps.springbootexample.entity.user.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
public class UserVO implements Serializable{

    private Integer id;

    private String userName;

    private Integer isDelete;

    private Date createTime;

    private Date updateTime;

    private Integer roleId;
    private String roleName;

    private Integer gender;
    private String genderName;

    @Override
    public String toString() {
        return "RoleVO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", isDelete=" + isDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", gender=" + gender +
                ", genderName='" + genderName + '\'' +
                '}';
    }
}
