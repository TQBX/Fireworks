package com.smday.fireworks.security.service;

import com.smday.fireworks.common.exception.ApiException;
import com.smday.fireworks.security.LoginUser;
import com.smday.fireworks.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 登录校验方法
 *
 * @author Summerday
 */

@Component
public class SysLoginService {

    private static final Logger logger = LoggerFactory.getLogger(SysLoginService.class);
    @Resource
    UserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

    /**
     * 用户登录成功,返回token
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    public String login(String username, String password) {
        UserDetails userDetails = null;
        try {
            userDetails = userDetailsService.loadUserByUsername(username);
        } catch (Exception e) {
            logger.info("登录异常: {}", e.getMessage());
            throw new ApiException(e.getMessage());
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return tokenService.createToken((LoginUser) userDetails);
    }

}
