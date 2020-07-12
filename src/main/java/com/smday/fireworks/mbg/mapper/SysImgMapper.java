package com.smday.fireworks.mbg.mapper;

import com.smday.fireworks.mbg.model.SysImg;
import com.smday.fireworks.mbg.model.SysImgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysImgMapper {
    long countByExample(SysImgExample example);

    int deleteByExample(SysImgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysImg record);

    int insertSelective(SysImg record);

    List<SysImg> selectByExample(SysImgExample example);

    SysImg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysImg record, @Param("example") SysImgExample example);

    int updateByExample(@Param("record") SysImg record, @Param("example") SysImgExample example);

    int updateByPrimaryKeySelective(SysImg record);

    int updateByPrimaryKey(SysImg record);
}