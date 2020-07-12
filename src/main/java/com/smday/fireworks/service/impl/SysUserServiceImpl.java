package com.smday.fireworks.service.impl;

import com.smday.fireworks.common.constants.UserConstants;
import com.smday.fireworks.mbg.mapper.SysUserMapper;
import com.smday.fireworks.mbg.model.SysUser;
import com.smday.fireworks.mbg.model.SysUserExample;
import com.smday.fireworks.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Summerday
 */
@Service
public class SysUserServiceImpl implements IUserService {


    @Resource
    SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserByUsername(String username) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        return sysUsers.get(0);

    }

    @Override
    public int insertUser(SysUser user) {
        return sysUserMapper.insertSelective(user);
    }

    @Override
    public String checkUsernameUnique(String username) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        if(sysUsers.size()>0){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public SysUser getUserByCode(String validateCode) {
        SysUser user = null;
        SysUserExample example = new SysUserExample();
        example.createCriteria().andActiveCodeEqualTo(validateCode);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        user = sysUsers.size() != 0?sysUsers.get(0):null;
        return user;
    }

    @Override
    public void setStatus(SysUser user) {
        sysUserMapper.updateByPrimaryKey(user);
    }
}
