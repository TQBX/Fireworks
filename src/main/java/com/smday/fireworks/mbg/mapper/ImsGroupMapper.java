package com.smday.fireworks.mbg.mapper;

import com.smday.fireworks.mbg.model.ImsGroup;
import com.smday.fireworks.mbg.model.ImsGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ImsGroupMapper {
    long countByExample(ImsGroupExample example);

    int deleteByExample(ImsGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ImsGroup record);

    int insertSelective(ImsGroup record);

    List<ImsGroup> selectByExample(ImsGroupExample example);

    ImsGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ImsGroup record, @Param("example") ImsGroupExample example);

    int updateByExample(@Param("record") ImsGroup record, @Param("example") ImsGroupExample example);

    int updateByPrimaryKeySelective(ImsGroup record);

    int updateByPrimaryKey(ImsGroup record);
}