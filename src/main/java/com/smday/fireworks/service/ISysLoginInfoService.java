package com.smday.fireworks.service;

import com.smday.fireworks.mbg.model.SysLoginInfo;

/**
 * 系统访问日志情况信息 服务层
 *
 * @author Summerday
 */
public interface ISysLoginInfoService {


  /**
   * 新增系统登录日志
   * @param loginInfo 访问日志对象
   */
  public void insertLoginInfo(SysLoginInfo loginInfo) ;
}
