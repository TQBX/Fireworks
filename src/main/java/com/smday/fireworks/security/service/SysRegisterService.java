package com.smday.fireworks.security.service;

import com.smday.fireworks.common.constants.Constants;
import com.smday.fireworks.common.constants.UserConstants;
import com.smday.fireworks.common.exception.ApiException;
import com.smday.fireworks.common.utils.IdUtils;
import com.smday.fireworks.common.utils.SecurityUtils;
import com.smday.fireworks.common.utils.bean.BeanUtils;
import com.smday.fireworks.component.JavaMailComponent;
import com.smday.fireworks.dto.UserRegisterParam;
import com.smday.fireworks.mbg.model.SysUser;
import com.smday.fireworks.nosql.redis.RedisCache;
import com.smday.fireworks.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 注册校验
 * @author Summerday
 */

@Component
public class SysRegisterService {

    @Autowired
    TokenService tokenService;

    @Autowired
    RedisCache redisCache;

    @Autowired
    IUserService userService;

    @Autowired
    JavaMailComponent javaMail;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.mail.subject}")
    private String subject;


    public SysUser register(UserRegisterParam registerParam){

        //获取uuid
        String uuid = registerParam.getUuid();

        String code = registerParam.getCode();

        //copy user属性
        SysUser user = new SysUser();
        BeanUtils.copyBeanProp(user, registerParam);

        //验证码校验
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            //todo 可以记录用户注册的日志
            throw new ApiException("验证码已过期");
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            //todo 可以记录用户注册的日志
            throw new ApiException("验证码不正确");
        }

        //用户名唯一性校验
        if(UserConstants.NOT_UNIQUE.equals(userService.checkUsernameUnique(user.getUsername()))){
            throw new ApiException("注册账号失败:"+user.getUsername()+"用户名已经存在");
        }

        //加密密码
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setCreateTime(new Date());

        //暂时设置为未激活
        user.setStatus(UserConstants.NO_ACTIVE);
        //生成随机码5位,存入cache中

        String activeCode = IdUtils.fastSimpleUUID().substring(0, 5);
        user.setActiveCode(activeCode);
        userService.insertUser(user);

        javaMail.send(subject,user.getEmail(),activeCode);
        return user;
    }

}
