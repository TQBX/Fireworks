package com.smday.fireworks.security.service;

import com.smday.fireworks.common.constants.UserConstants;
import com.smday.fireworks.common.exception.BaseException;
import com.smday.fireworks.common.utils.StringUtils;
import com.smday.fireworks.mbg.model.SysUser;
import com.smday.fireworks.security.LoginUser;
import com.smday.fireworks.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * created by Summer-day
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名获取数据库中的用户
        SysUser user = userService.getUserByUsername(username);

        if (StringUtils.isNull(user)) {
            logger.info("登录用户: {}不存在", username);
            throw new UsernameNotFoundException("用户未注册");
        }
        if (UserConstants.NO_ACTIVE.equals(user.getStatus())) {
            logger.info("登录用户: {}未激活", username);
            throw new BaseException("用户未激活");
        }
        return createLoginUser(user);
    }
    private UserDetails createLoginUser(SysUser user) {
        //管理员
        Set<String> permissions = new HashSet<>();
        permissions.add("*:*:*");
        return new LoginUser(permissions, user);
    }
}
