package com.smday.fireworks.security.service;

import com.smday.fireworks.common.constants.Constants;
import com.smday.fireworks.common.exception.CustomException;
import com.smday.fireworks.common.exception.user.CaptchaException;
import com.smday.fireworks.common.exception.user.CaptchaExpireException;
import com.smday.fireworks.common.exception.user.UserPasswordNotMatchException;
import com.smday.fireworks.common.manager.AsyncManager;
import com.smday.fireworks.common.manager.factory.AsyncFactory;
import com.smday.fireworks.common.utils.MessageUtils;
import com.smday.fireworks.nosql.redis.RedisCache;
import com.smday.fireworks.security.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * 登录校验方法
 * @author Summerday
 */

@Component
public class SmsLoginService {

  @Autowired
  private TokenService tokenService;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private RedisCache redisCache;

  public String login(String username,String password,String code,String uuid){

    String verifyKey = Constants.CAPTCHA_CODE_KEY+uuid;
    String captcha = redisCache.getCacheObject(verifyKey);
    if(captcha == null){
      AsyncManager.me().execute(AsyncFactory.recordLogininfor(username,Constants.LOGIN_FAIL, "验证码已过期"));
      throw new CaptchaExpireException();
    }
    if(!code.equalsIgnoreCase(captcha)){
      AsyncManager.me().execute(AsyncFactory.recordLogininfor(username,Constants.LOGIN_FAIL, "验证码已过期"));
      throw new CaptchaException();
    }

    //用户验证
    Authentication authentication = null;
    try{
      authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }catch (Exception e){
      if (e instanceof BadCredentialsException)
      {
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
        throw new UserPasswordNotMatchException();
      }
      else
      {
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
        throw new CustomException(e.getMessage());
      }
    }
    AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
    LoginUser loginUser = (LoginUser) authentication.getPrincipal();
    // 生成token
    return tokenService.createToken(loginUser);
  }

}
