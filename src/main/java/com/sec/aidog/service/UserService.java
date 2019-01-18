package com.sec.aidog.service;

import com.sec.aidog.pojo.Managers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UserService {
    Managers userLogin(String username, String pwd);

    Map<String, Integer> GetIndexLogoInfo(Managers resultUser) throws Exception;

    Map<String, Object> GetCountryMap() throws Exception;

    List<Map<String,String>> GetAllCities() throws Exception;

    List<Map<String,String>> GetAllCounties() throws Exception;

    List<Map<String,String>> GetAllVillages() throws Exception;

    List<Map<String,String>> GetAllHamlets() throws Exception;
}
