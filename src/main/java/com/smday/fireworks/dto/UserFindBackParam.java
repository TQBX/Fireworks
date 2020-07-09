package com.smday.fireworks.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户忘记密码传入参数
 * created by Summer-day
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class UserFindBackParam {
	
	@ApiModelProperty(value = "用户名")
	private String phone;
	
	
	@ApiModelProperty(value = "邮箱")
	private String eMail;
	
	
}
