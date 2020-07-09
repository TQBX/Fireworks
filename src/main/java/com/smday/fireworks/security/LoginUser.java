package com.smday.fireworks.security;

import com.smday.fireworks.mbg.model.UmsUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * 登录用户身份权限
 * created by Summer-day
 */
@Data
public class LoginUser implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 唯一身份标识
	 */
	private String token;
	
	/**
	 * 登陆时间
	 */
	private Long loginTime;
	
	/**
	 * 过期时间
	 */
	private Long expireTime;
	
	/**
	 * 登录IP地址
	 */
	private String ipaddr;
	
	/**
	 * 登录地点
	 */
	private String loginLocation;
	
	/**
	 * 浏览器类型
	 */
	private String browser;
	
	/**
	 * 操作系统
	 */
	private String os;
	
	/**
	 * 权限列表
	 */
	private Set<String> permissions;
	
	/**
	 * 用户信息
	 */
	private UmsUser user;
	
	public LoginUser(){
	
	}
	
	public LoginUser(Set<String> permissions, UmsUser user) {
		this.permissions = permissions;
		this.user = user;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	@Override
	public String getPassword() {
		return user.getOldPassword();
	}
	
	@Override
	public String getUsername() {
		return user.getUsername();
	}
	/**
	 * 账户是否未过期,过期无法验证
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	/**
	 * 指定用户是否解锁,锁定的用户无法进行身份验证
	 *
	 * @return
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	/**
	 * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
	 *
	 * @return
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	/**
	 * 是否可用 ,禁用的用户不能身份验证
	 *
	 * @return
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
}
