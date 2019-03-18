package com.sec.aidog.service.impl;

import com.sec.aidog.dao.DogMapper;
import com.sec.aidog.dao.ManagerMapper;
import com.sec.aidog.pojo.Dog;
import com.sec.aidog.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("dogService")
public class DogServiceImpl implements DogService{

    @Autowired
    private DogMapper dogMapper;

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public String addDog(String username, String dogname, String dogsex, String dogbelonghamlet, String ownerhamletcode, String dogownerid,
                         String dogweight, String dogcolor, int dogage,String govcode) throws Exception {
        String result = "添加失败";

        Dog sheepdog = new Dog();
        sheepdog.setDogName(dogname);
        sheepdog.setNecId("-1");
        sheepdog.setAppId("-1");
        sheepdog.setUsername(username);
        sheepdog.setManagerName(managerMapper.findUserByName(username).getManagerName());
        sheepdog.setDogownerId(Integer.parseInt(dogownerid));
        sheepdog.setDogWeight(dogweight);
        sheepdog.setDogColor(dogcolor);
        sheepdog.setDogAge(dogage);
        sheepdog.setDogInfo("健康");
        sheepdog.setDogStatus("1");
        sheepdog.setDogRegistertime(new Date());
        sheepdog.setDogSex(dogsex);
        sheepdog.setDistrictcode(ownerhamletcode);
        sheepdog.setDogGovcode(govcode);

        boolean flag = dogMapper.insert(sheepdog)==0?false:true;
        if(flag){
            result = "牧犬添加成功！";
        }
        return result;
    }
}
