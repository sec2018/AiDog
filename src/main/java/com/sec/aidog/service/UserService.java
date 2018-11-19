package com.sec.aidog.service;

import org.springframework.stereotype.Component;

@Component
public interface UserService {
    boolean userLogin(String username, String pwd);
}
