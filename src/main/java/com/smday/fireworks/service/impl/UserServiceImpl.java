package com.smday.fireworks.service.impl;

import com.smday.fireworks.common.exception.ApiException;
import com.smday.fireworks.component.JavaMailComponent;
import com.smday.fireworks.dto.UserFindBackParam;
import com.smday.fireworks.dto.UserLoginParam;
import com.smday.fireworks.mbg.mapper.UmsUserMapper;
import com.smday.fireworks.mbg.model.UmsUser;
import com.smday.fireworks.mbg.model.UmsUserExample;
import com.smday.fireworks.service.IUserService;
import jdk.nashorn.internal.objects.NativeUint8Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

import static org.springframework.util.DigestUtils.md5DigestAsHex;

/**
 * created by Summer-day
 */

@Service
public class UserServiceImpl implements IUserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UmsUserMapper userMapper;
	@Autowired
	private JavaMailComponent javaMail;
	
	@Value("${spring.mail.subject}")
	private String subject;
	
	//@Autowired
	//private UmsUserExample userExample;
	@Override
	public boolean checkLogin(String username, String password) {
		UmsUserExample userExample = new UmsUserExample();
		userExample.createCriteria().andOldPasswordEqualTo(password).andUsernameEqualTo(username);
		List<UmsUser> umsUsers = userMapper.selectByExample(userExample);
		return umsUsers.size() == 1;
	}
	
	@Override
	public UmsUser register(UmsUser user, HttpServletRequest request) {
		//判断验证吗
		HttpSession session = request.getSession();
		String trueCode = (String) session.getAttribute("verifyCode");
		String verifyCode = user.getVerifyCode();
		if(verifyCode == null || "".equals(verifyCode) || !verifyCode.equalsIgnoreCase(trueCode)) {
			throw new ApiException("验证码错误");
		}
		session.removeAttribute("verifyCode");
		
		//查询是否有同名的用户
		if(checkUsername(user.getUsername())) {
			throw new ApiException("用户名已经存在");
		}
		
		//加密密码设置
		String encodePassword = md5DigestAsHex(user.getOldPassword().getBytes());
		user.setOldPassword(encodePassword);
		
		//生成随机字符串
		String validateCode = UUID.randomUUID().toString().substring(0, 5);
		user.setValidateCode(validateCode);
		
		//设置未激活
		user.setCodeStatus(0);
		
		this.saveUser(user);
		
		javaMail.send(subject, user.geteMail(), validateCode);
		
		return user;
		
	}
	
	
	@Override
	public boolean checkUsername(String username) {
		UmsUserExample userExample = new UmsUserExample();
		userExample.createCriteria().andUsernameEqualTo(username);
		List<UmsUser> umsUsers = userMapper.selectByExample(
				userExample);
		return umsUsers.size() > 0;
	}
	
	@Override
	public UmsUser findUserByCode(String code) {
		UmsUserExample example = new UmsUserExample();
		example.createCriteria().andValidateCodeEqualTo(code);
		List<UmsUser> umsUsers = userMapper.selectByExample(example);
		return umsUsers.get(0);
	}
	
	@Override
	public void setStatus(UmsUser user) {
		userMapper.updateByPrimaryKey(user);
	}
	
	@Override
	public void saveUser(UmsUser user) {
		userMapper.insert(user);
	}
	
	@Override
	public int getCodeStatus(String username) {
		UmsUserExample example = new UmsUserExample();
		example.createCriteria().andUsernameEqualTo(username);
		List<UmsUser> umsUsers = userMapper.selectByExample(example);
		return umsUsers.get(0).getCodeStatus();
	}
	
	@Override
	public UmsUser getUserByUsername(String username) {
		UmsUserExample example = new UmsUserExample();
		example.createCriteria().andUsernameEqualTo(username);
		List<UmsUser> umsUsers = userMapper.selectByExample(example);
		return umsUsers.get(0);
	}
	
	@Override
	public int update(UmsUser user) {
		return userMapper.updateByPrimaryKey(user);
	}
	
	@Override
	public UmsUser login(UserLoginParam userLoginParam) {
		UmsUser user = null;
		try {
			//先判断用户是否存在
			user = getUserByUsername(userLoginParam.getUsername());
			if(user == null) {
				throw new ApiException("用户名不存在");
			}
			//再判断是否激活
			if(user.getCodeStatus() == 0) {
				throw new ApiException("账号未激活");
			}
			//判断密码是否正确
			
			String oldPassword = userLoginParam.getOldPassword();
			oldPassword = md5DigestAsHex(oldPassword.getBytes());
			if(!checkLogin(userLoginParam.getUsername(), oldPassword)) {
				throw new ApiException("用户名或密码错误");
			}
		} catch (Exception e) {
			LOGGER.warn("登录错误:{}", e.getMessage());
			throw e;
		}
		return user;
	}
	
	@Override
	public UmsUser findBackPassword(UserFindBackParam userFindBackParam) {
		
		if(userFindBackParam.getEMail()!= null){
			
			//邮箱发送验证码
		}
		return null;
	}
	
	
}
