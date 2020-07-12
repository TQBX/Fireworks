package com.smday.fireworks.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * 用户登录dto
 * @author Summerday
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginParam {

    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "五位随机码",required = true)
    private String validateCode;

}
