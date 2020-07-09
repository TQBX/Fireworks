package com.smday.fireworks.mbg.mapper;

import com.smday.fireworks.mbg.model.ImsImg;
import com.smday.fireworks.mbg.model.ImsImgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ImsImgMapper {
    long countByExample(ImsImgExample example);

    int deleteByExample(ImsImgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ImsImg record);

    int insertSelective(ImsImg record);

    List<ImsImg> selectByExample(ImsImgExample example);

    ImsImg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ImsImg record, @Param("example") ImsImgExample example);

    int updateByExample(@Param("record") ImsImg record, @Param("example") ImsImgExample example);

    int updateByPrimaryKeySelective(ImsImg record);

    int updateByPrimaryKey(ImsImg record);
}