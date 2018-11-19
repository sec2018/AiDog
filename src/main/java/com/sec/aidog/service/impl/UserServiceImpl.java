package com.sec.aidog.service.impl;

import com.sec.aidog.dao.ManagersMapper;
import com.sec.aidog.pojo.Managers;
import com.sec.aidog.service.UserService;
import com.sec.aidog.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    private ManagersMapper managersMapper;

    @Override
    public Managers userLogin(String username, String pwd) {
        AESUtil util = new AESUtil();
        Managers manager = null;
        manager = managersMapper.findUserByName(username);
        try {
            if(util.decryptData(pwd).equals(manager.getPassword())){
                return manager;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
