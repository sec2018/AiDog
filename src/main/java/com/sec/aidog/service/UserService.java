package com.sec.aidog.service;

import com.sec.aidog.pojo.Managers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface UserService {
    Managers userLogin(String username, String pwd);

    Map<String, Integer> GetIndexLogoInfo(Managers resultUser) throws Exception;

    Map<String, Object> GetCountryMap() throws Exception;
}
