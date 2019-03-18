package com.sec.aidog.service.impl;

import com.sec.aidog.dao.DogownerMapper;
import com.sec.aidog.model.DogownerExample;
import com.sec.aidog.pojo.Dogowner;
import com.sec.aidog.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("ownerService")
public class OwnerServiceImpl implements OwnerService{

    @Autowired
    private DogownerMapper dogownerMapper;
    /**
     * 添加主人
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public String addOwner(String ownername, String owneridentity, String ownersex, String ownerhamletcode, int ownerage,
                          String ownerjob, String homeaddress, String telphone) throws Exception {
        String result = "添加失败";
        // 如果主人存在，则无法再次创建
        if (dogownerMapper.getOwnerByTelAndDistricode(telphone,ownerhamletcode)!=null) {
            result = "添加失败，主人已经存在！";
        }
        Dogowner owner = new Dogowner();
        owner.setOwnerName(ownername);
        owner.setOwnerTel(telphone);
        owner.setOwnerAddr(homeaddress);
        owner.setOwnerStatus(1);
        owner.setOwnerIdentity(owneridentity);
        owner.setOwnerSex(ownersex);
        owner.setOwnerAge(ownerage);
        owner.setOwnerJob(ownerjob);
        owner.setDistrictcode(ownerhamletcode);
        boolean flag = dogownerMapper.insert(owner)==0?false:true;
        if(flag){
            result = "主人添加成功！";
        }
        return result;
    }

    @Override
    public Map<String, Object> getHamletOwnersList(String hamletcode) {
        Map<String, Object> map = new HashMap<String, Object>();
        DogownerExample example = new DogownerExample();
        example.createCriteria().andDistrictcodeEqualTo(hamletcode);
        List<Dogowner> dogownersList = dogownerMapper.selectByExample(example);
        int i=0;
        for (Dogowner item:dogownersList)
        {
            Map<String, Object> maptemp = new HashMap<String, Object>();
            maptemp.put("ownerid", item.getOwnerId());
            maptemp.put("ownername", item.getOwnerName());
            map.put(""+i, maptemp);
            i++;
        }
        return map;
    }

}
