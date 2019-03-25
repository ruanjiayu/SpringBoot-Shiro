package com.fan.service.impl;

import com.fan.domain.Users;
import com.fan.customMapper.UsersMapper;
import com.fan.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ruanjiayu
 * @dateTime 2019/3/21 9:14
 */
@Service
public class UsersServiceImpl implements UsersService{

    /**注入Mapper接口*/
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Users findByName(String name) {
        return usersMapper.findByUserName(name);
    }


    @Override
    public Users findById(String id) {
        return usersMapper.findById(id);
    }
}
