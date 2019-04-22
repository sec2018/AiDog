package com.sec.aidog.service;

import com.sec.aidog.pojo.Dog;

import java.util.Map;

public interface DogService {

    Dog addDog(String username, String dogname, String dogsex, String dogbelonghamlet, String ownerhamletcode, String dogownerid,
               String dogweight, String dogcolor, int dogage, String govcode)  throws Exception;

    Map<String, Object> getDogList(String districtcode, int startPage, int pageSize);

    Map<String, Object> getDogInfo(Integer dogid);

    String modifyDog(Integer dogid, String dogname, String dogsex, String dogweight, String dogcolor, Integer dogage);
}
