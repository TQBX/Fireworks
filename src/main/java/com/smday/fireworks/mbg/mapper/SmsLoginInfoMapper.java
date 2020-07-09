package com.smday.fireworks.mbg.mapper;

import com.smday.fireworks.mbg.model.SmsLoginInfo;
import com.smday.fireworks.mbg.model.SmsLoginInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsLoginInfoMapper {
    long countByExample(SmsLoginInfoExample example);

    int deleteByExample(SmsLoginInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsLoginInfo record);

    int insertSelective(SmsLoginInfo record);

    List<SmsLoginInfo> selectByExample(SmsLoginInfoExample example);

    SmsLoginInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsLoginInfo record, @Param("example") SmsLoginInfoExample example);

    int updateByExample(@Param("record") SmsLoginInfo record, @Param("example") SmsLoginInfoExample example);

    int updateByPrimaryKeySelective(SmsLoginInfo record);

    int updateByPrimaryKey(SmsLoginInfo record);
}