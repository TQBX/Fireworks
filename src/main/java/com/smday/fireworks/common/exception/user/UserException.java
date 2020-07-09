package com.smday.fireworks.common.exception.user;

import com.smday.fireworks.common.exception.BaseException;

/**
 * 用户信息异常类
 * @author Summerday
 */
public class UserException extends BaseException {


  private static final long serialVersionID = 1L;

  public UserException(String code, Object[] args) {
    super("user", code, args, null);
  }
}
