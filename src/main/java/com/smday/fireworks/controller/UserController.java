package com.smday.fireworks.controller;

import com.smday.fireworks.common.api.CommonResult;
import com.smday.fireworks.dto.UserFindBackParam;
import com.smday.fireworks.dto.UserLoginParam;
import com.smday.fireworks.mbg.model.UmsUser;
import com.smday.fireworks.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理控制器
 * created by Summer-day
 */

@RestController
@RequestMapping("/user")
@Api(tags = "UserController", description = "用户管理")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private IUserService userService;
	
	@PostMapping("/login")
	@ApiOperation("登录功能")
	public CommonResult<Map<String, Object>> login(@RequestBody UserLoginParam userLoginParam, HttpServletRequest request) {
		UmsUser user = null;
		try {
			user = userService.login(userLoginParam);
		} catch (Exception e) {
			return CommonResult.failed(e.getMessage());
		}
		Date loginTime = new Date();
		//更新最后登录的时间
		user.setLoginTime(loginTime);
		userService.update(user);
		//存入session域中
		request.getSession().setAttribute("user", user);
		Map<String, Object> info = new HashMap<>();
		info.put("username", user.getUsername());
		info.put("loginTime", loginTime);
		return CommonResult.success(info, "登录成功");
	}
	
	@PostMapping("/register")
	@ApiOperation("注册功能")
	public CommonResult<UmsUser> register(@RequestBody UmsUser umsUser, HttpServletRequest request) {
		UmsUser user = null;
		try {
			//注册并返回user
			user = userService.register(umsUser, request);
		} catch (Exception e) {
			LOGGER.info("发生错误:{}", e.getMessage());
			return CommonResult.failed(e.getMessage());
		}
		return CommonResult.success(user, "注册成功!");
	}
	
	@ApiOperation("激活账号")
	@PostMapping("/active")
	public CommonResult<UmsUser> activeCode(@RequestBody UserLoginParam userLoginParam) {
		String validateCode = userLoginParam.getValidateCode();
		//得到code参数的值
		UmsUser umsUser = userService.findUserByCode(validateCode);
		if(umsUser == null) {
			return CommonResult.failed("激活码错误");
		}
		umsUser.setCodeStatus(1);
		//设置user的激活状态为1
		userService.setStatus(umsUser);
		return CommonResult.success(umsUser, "成功");
	}
	
	//todo
	@ApiOperation("找回密码")
	@GetMapping("/findBack")
	public CommonResult findBackPassword(@RequestBody UserFindBackParam userFindBackParam) {
		
		try {
			userService.findBackPassword(userFindBackParam);
		} catch (Exception e) {
			LOGGER.warn("操作异常:{}", e.getMessage());
			return CommonResult.failed(e.getMessage());
		}
		return CommonResult.success("密码已修改");
	}
	
	
	@GetMapping("/info")
	public CommonResult<UmsUser> getInfo(HttpServletRequest request) {
		UmsUser user = (UmsUser) request.getSession().getAttribute("user");
		return CommonResult.success(user);
	}
	
	@PutMapping("/info")
	public CommonResult<UmsUser> updateInfo(UmsUser user, HttpServletRequest request) {
		HttpSession session = request.getSession();
		//从域中获取user
		UmsUser sessionUser = (UmsUser) session.getAttribute("user");
		
		if(user.getId() != sessionUser.getId()) {
			return CommonResult.failed("出现异常!");
		}
		if(userService.update(user) != 1) {
			return CommonResult.failed("更新用户信息失败");
		}
		session.setAttribute("user", user);
		return CommonResult.success(user, "用户信息更新成功");
	}
}
