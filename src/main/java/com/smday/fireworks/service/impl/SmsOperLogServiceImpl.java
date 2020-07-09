package com.smday.fireworks.service.impl;

import com.smday.fireworks.mbg.mapper.SmsOperLogMapper;
import com.smday.fireworks.mbg.model.SmsOperLog;
import com.smday.fireworks.service.ISmsOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by Summer-day
 */
@Service
public class SmsOperLogServiceImpl implements ISmsOperLogService {
	
	@Autowired
	private SmsOperLogMapper smsOperLogMapper;
	
	/**
	 * 新增操作日志
	 * @param operLog
	 */
	@Override
	public void insertOperLog(SmsOperLog operLog) {
		smsOperLogMapper.insert(operLog);
	}
}
