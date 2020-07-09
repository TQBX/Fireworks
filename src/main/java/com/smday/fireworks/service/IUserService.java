package com.smday.fireworks.service;

import com.smday.fireworks.dto.UserFindBackParam;
import com.smday.fireworks.dto.UserLoginParam;
import com.smday.fireworks.mbg.model.UmsUser;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户管理service
 * created by Summer-day
 */
public interface IUserService {
	
	/**
	 * 登录
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	boolean checkLogin(String username, String password);
	
	/**
	 * 注册,返回注册行数
	 *
	 * @param umsUser
	 * @return
	 */
	UmsUser register(UmsUser umsUser, HttpServletRequest request);
	
	/**
	 * 判断用户名是否存在
	 *
	 * @param username
	 * @return
	 */
	boolean checkUsername(String username);
	
	//boolean checkCode(String code);
	
	/**
	 * 获取验证码的用户
	 *
	 * @param code
	 * @return
	 */
	UmsUser findUserByCode(String code);
	
	void setStatus(UmsUser user);
	
	void saveUser(UmsUser user);
	
	int getCodeStatus(String username);
	
	UmsUser getUserByUsername(String username);
	
	int update(UmsUser user);
	
	UmsUser login(UserLoginParam userLoginParam);
	
	UmsUser findBackPassword(UserFindBackParam username);
}
