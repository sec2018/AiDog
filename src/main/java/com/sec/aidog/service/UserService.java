package com.sec.aidog.service;

import com.sec.aidog.pojo.Managers;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    Managers userLogin(String username, String pwd);
}
