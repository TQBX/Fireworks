package com.smday.fireworks.service;

import com.smday.fireworks.mbg.model.SysUser;

/**
 * @author Summerday
 */
public interface IUserService {


    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    SysUser getUserByUsername(String username);

    /**
     * 插入用户
     * @param user
     * @return
     */
    int insertUser(SysUser user);

    /**
     * 校验用户名是否唯一
     * @param username
     * @return
     */
    String checkUsernameUnique(String username);

    /**
     * 根据激活码获取用户
     * @param validateCode
     * @return
     */
    SysUser getUserByCode(String validateCode);

    /**
     * 激活
     * @param user
     */
    void setStatus(SysUser user);
}
