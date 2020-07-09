package com.smday.fireworks.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * 用户登录参数
 * created by Summer-day
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class UserLoginParam {
	
	@ApiModelProperty(value = "用户名",required = true)
	@NotEmpty(message = "用户名不能为空")
	private String username;
	
	
	@ApiModelProperty(value = "密码",required = true)
	@NotEmpty(message = "密码不能为空")
	private String oldPassword;
	
	@ApiModelProperty(value = "随机码",required = true)
	@NotEmpty(message = "随机不能为空")
	private String validateCode;
	
}
