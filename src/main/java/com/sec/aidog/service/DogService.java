package com.sec.aidog.service;

public interface DogService {

    String addDog(String username, String dogname, String dogsex, String dogbelonghamlet, String ownerhamletcode, String dogownerid,
                         String dogweight, String dogcolor, int dogage, String govcode)  throws Exception;
}
