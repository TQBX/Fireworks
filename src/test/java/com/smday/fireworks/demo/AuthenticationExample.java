package com.smday.fireworks.demo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Summer-day
 */
public class AuthenticationExample  {
	private static AuthenticationManager am = new SampleAuthenticationManager();
	public static void main(String[] args) throws IOException {
		BufferedReader in  = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			
			System.out.println("please enter your username: ");
			String name = in.readLine();
			System.out.println("please enter your password: ");
			String password = in.readLine();
			
			try {
				//用户名和密码被组合到UsernamePasswordAuthenticationToken实例中
				Authentication request = new UsernamePasswordAuthenticationToken(name, password);
				//传递到AuthenticationManager实例中进行验证,身份验证成功后将返回一个完全填充的身份验证实例
				Authentication authentication = am.authenticate(request);
				//为安全上下文中传入身份验证对象,之后就可以对用户进行身份验证.
				SecurityContextHolder.getContext().setAuthentication(authentication);
				break;
			}catch (AuthenticationException e){
				System.out.println("authentication failed: "+e.getMessage());
			}
			
		}
		System.out.println("successfully authenticated security context contains: "+SecurityContextHolder.getContext().getAuthentication());
	}
	
}
class SampleAuthenticationManager implements AuthenticationManager{
	static final List<GrantedAuthority> AUTHORITIES = new ArrayList<>();
	static {
		AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
	}
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		//身份验证成功后将返回一个完全填充的身份验证实例
		if(auth.getName().equals(auth.getCredentials())){
			return new UsernamePasswordAuthenticationToken(auth.getName(),auth.getCredentials(),AUTHORITIES);
		}
		throw new BadCredentialsException("bad credentials");
	}
}