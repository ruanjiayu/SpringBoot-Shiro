package com.fan.service;

import com.fan.domain.Users;

/**
 * @author ruanjiayu
 * @dateTime 2019/3/21 9:12
 */
public interface UsersService {

    /**
     * 通过来查询用户
     * @param name
     * @return
     */
    Users findByName(String name);

    /**
     * 通过id来查询对应的权限
     * @param id
     * @return
     */
    Users findById(String id);
}
