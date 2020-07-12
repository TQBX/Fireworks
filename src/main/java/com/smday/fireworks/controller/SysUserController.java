package com.smday.fireworks.controller;

import com.smday.fireworks.common.api.AjaxResult;
import com.smday.fireworks.dto.UserLoginParam;
import com.smday.fireworks.dto.UserRegisterParam;
import com.smday.fireworks.mbg.model.SysUser;
import com.smday.fireworks.security.service.SysLoginService;
import com.smday.fireworks.security.service.SysRegisterService;
import com.smday.fireworks.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author Summerday
 */

@RestController
@RequestMapping("/user")
@Api(tags = "UserController", description = "用户管理")
public class SysUserController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    SysRegisterService sysRegisterService;

    @Autowired
    SysLoginService sysLoginService;

    @PostMapping("/register")
    @ApiOperation("注册功能")
    public AjaxResult register(@RequestBody UserRegisterParam userRegisterParam){

        AjaxResult ajaxResult = AjaxResult.success();
        SysUser user =null;
        try {
           user = sysRegisterService.register(userRegisterParam);
        }catch (Exception e){
            logger.warn("注册异常:{}",e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
        ajaxResult.put("user",user);
        return ajaxResult;
    }

    @PostMapping("/login")
    @ApiOperation("登录功能")
    public AjaxResult login(@RequestBody UserLoginParam userLoginParam){

        AjaxResult ajaxResult = AjaxResult.success();
        String token = sysLoginService.login(userLoginParam.getUsername(), userLoginParam.getPassword());
        ajaxResult.put("token",token);
        return ajaxResult;
    }

    @PostMapping("/active")
    @ApiOperation("激活功能")
    public AjaxResult active(@RequestBody UserLoginParam userLoginParam){

        String validateCode = userLoginParam.getValidateCode();

        SysUser user =  userService.getUserByCode(validateCode);
        if(user == null){
            return AjaxResult.error("激活码错误");
        }
        user.setStatus("1");
        userService.setStatus(user);

        AjaxResult ajax = AjaxResult.success("激活成功");
        ajax.put("user",user);
        return ajax;
    }

}
