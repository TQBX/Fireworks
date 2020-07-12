package com.smday.fireworks.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * 用户注册参数
 * created by Summer-day
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class UserRegisterParam {

  @ApiModelProperty(value = "用户名", required = true)
  @NotEmpty(message = "用户名不能为空")
  private String username;

  @ApiModelProperty(value = "性别")
  private Integer sex;

  @ApiModelProperty(value = "密码", required = true)
  @NotEmpty(message = "密码不能为空")
  private String password;

  @ApiModelProperty(value = "电话")
  @NotEmpty(message = "电话不能为空")
  private String phone;

  @ApiModelProperty(value = "邮箱")
  //@Email(message = "邮箱格式不合法")
  private String email;

  @ApiModelProperty(value = "生日")
  private Date birthday;

  @ApiModelProperty(value = "验证码")
  private String code;

  @ApiModelProperty(value = "随机码")
  private String uuid;

}
