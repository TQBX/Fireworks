package com.smday.fireworks.service.impl;

import com.smday.fireworks.mbg.mapper.SmsLoginInfoMapper;
import com.smday.fireworks.mbg.model.SmsLoginInfo;
import com.smday.fireworks.service.ISmsLoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 操作日志记录服务实现类
 * created by Summer-day
 */
@Service
public class SmsLoginInfoServiceImpl implements ISmsLoginInfoService {
	
	@Autowired
	SmsLoginInfoMapper smsLoginInfoMapper;
	
	/**
	 * 新增系统登录日志
	 *
	 * @param loginInfo 访问日志对象
	 */
	@Override
	public void insertLoginInfo(SmsLoginInfo loginInfo) {
		smsLoginInfoMapper.insert(loginInfo);
	}
}
