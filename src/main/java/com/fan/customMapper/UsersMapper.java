package com.fan.customMapper;

import com.fan.domain.Users;

/**
 * @author ruanjiayu
 * @dateTime 2019/3/20 17:34
 */
public interface UsersMapper {

    /**
     * 通过名字来进行查询
     * @param username
     * @return
     */
    Users findByUserName(String username);

    /**
     * 通过id来查询对应的权限
     * @param id
     * @return
     */
    Users findById(String id);
}
