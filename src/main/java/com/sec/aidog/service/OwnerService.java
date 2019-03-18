package com.sec.aidog.service;

import java.util.Map;

public interface OwnerService {

    String addOwner(String ownername, String owneridentity, String ownersex,String ownerhamletcode, int ownerage, String ownerjob, String homeaddress, String telphone) throws Exception;

    Map<String, Object> getHamletOwnersList(String hamletcode);
}
