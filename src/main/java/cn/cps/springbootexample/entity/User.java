package cn.cps.springbootexample.entity;

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
 * @Description: 用户实体类  实战中会使用不同的实体类；
 * DTO数据传输对象(前端到后台) VO值对象(后台到前端) PO持久对象(跟数据库字段一一对应)

 * Jackson提供了一系列注解，方便对JSON序列化和反序列化进行控制，下面介绍一些常用的注解。
 * @JsonIgnore 此注解用于属性上，作用是进行JSON操作时忽略该属性。
 * @JsonFormat 此注解用于属性上，作用是把Date类型直接转化为想要的格式，如@JsonFormat(pattern = "yyyy年MM月dd日")
 * @JsonProperty 此注解用于属性上，作用是把该属性的名称序列化为另外一个名称，如把eMail属性序列化为mail，@JsonProperty("mail")

 */
@Data
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User implements Serializable{

    //Mybatis-Plus主键类型
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "用户ID")
    private Integer id;

    //Mybatis-Plus属性与字段自定义映射
    @TableField("username")
    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "性别(0/1)")
    private Integer gender;

    @ApiModelProperty(value = "是否被禁用")
    private Integer isDisable;

    @ApiModelProperty(value = "是否被删除")
    private Integer isDelete;

    //Jackson注解把Date类型直接转化为想要的格式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 格式化日期属性
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    //Jackson注解把Date类型直接转化为想要的格式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 格式化日期属性
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


    //分页条件

    //@TableField(exist = false) Mybatis-Plus 注解即可忽略掉字段映射
    @TableField(exist = false)
    @ApiModelProperty(value = "当前页")
    private Integer current;

    //@TableField(exist = false) Mybatis-Plus 注解即可忽略掉字段映射
    @TableField(exist = false)
    @ApiModelProperty(value = "页容量")
    private Integer pageSize;


    //扩展属性
    //@TableField(exist = false) Mybatis-Plus 注解即可忽略掉字段映射
    @TableField(exist = false)
    @ApiModelProperty(value = "性别(男/女)")
    private String genderName;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", isDisable=" + isDisable +
                ", isDelete=" + isDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", current=" + current +
                ", pageSize=" + pageSize +
                ", genderName='" + genderName + '\'' +
                '}';
    }
}
