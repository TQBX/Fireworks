package com.smday.fireworks.mbg.mapper;

import com.smday.fireworks.mbg.model.SmsOperLog;
import com.smday.fireworks.mbg.model.SmsOperLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsOperLogMapper {
    long countByExample(SmsOperLogExample example);

    int deleteByExample(SmsOperLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SmsOperLog record);

    int insertSelective(SmsOperLog record);

    List<SmsOperLog> selectByExample(SmsOperLogExample example);

    SmsOperLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SmsOperLog record, @Param("example") SmsOperLogExample example);

    int updateByExample(@Param("record") SmsOperLog record, @Param("example") SmsOperLogExample example);

    int updateByPrimaryKeySelective(SmsOperLog record);

    int updateByPrimaryKey(SmsOperLog record);
}