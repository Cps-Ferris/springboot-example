package cn.cps.springbootexample.entity.user.to;

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
 * @Description: 用户DTO实体类(前端到后台)
 */
@Data
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class UserTO implements Serializable{

    @ApiModelProperty(value = "当前页")
    private Integer current;

    @ApiModelProperty(value = "页容量")
    private Integer pageSize;


    @ApiModelProperty(value = "用户ID")
    private Integer id;

    @ApiModelProperty(value = "用户角色")
    private String roleId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户性别")
    private Integer gender;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
