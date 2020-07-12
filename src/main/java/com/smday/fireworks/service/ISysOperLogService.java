package com.smday.fireworks.service;

import com.smday.fireworks.mbg.model.SysOperLog;

/**
 * 系统操作日志服务
 * @author Summerday
 */
public interface ISysOperLogService {


  /**
   * 添加系统操作日志
   * @param operLog 操作日志对象
   */
  void insertOperLog(SysOperLog operLog);
}
