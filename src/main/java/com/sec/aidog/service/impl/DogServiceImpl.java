package com.sec.aidog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sec.aidog.dao.*;
import com.sec.aidog.pojo.*;
import com.sec.aidog.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("dogService")
public class DogServiceImpl implements DogService{

    @Autowired
    private DogMapper dogMapper;

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private LastnecdosingMapper lastnecdosingMapper;

    @Autowired
    private LastappdosingMapper lastappdosingMapper;

    @Autowired
    private DogownerMapper dogownerMapper;

    @Autowired
    private DistrictMapper districtMapper;

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

    @Override
    public Map<String, Object> getDogList(String districtcode, int startPage, int pageSize) {
        Page page = PageHelper.startPage(startPage, pageSize);
        List<DogView> doglist = dogMapper.getDogListByDistrictcode(districtcode);
        Map<String, Object> map = new HashMap<String,Object>();
        //每页信息
        map.put("data", doglist);
        //管理员总数
        map.put("totalNum", page.getTotal());
        return map;
    }

    @Override
    public Map<String, Object> getDogInfo(Integer dogid) {
        Dog dog  = dogMapper.selectByPrimaryKey(dogid);
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("dog",dog);
        String admintel = managerMapper.findUserByName(dog.getUsername()).getManagerTel();
        map.put("admintel",admintel);
        String hamlet = districtMapper.selectByDistrictCode(dog.getDistrictcode()).getDistrictName();
        map.put("hamlet",hamlet);
        String nec_id = dog.getNecId();
        if(!nec_id.equals("-1")){
            Lastnecdosing lastnecdosing = lastnecdosingMapper.getLastnecdosing(nec_id);
            map.put("nec",lastnecdosing);
        }
        String app_id = dog.getAppId();
        if(!app_id.equals("-1")){
            Lastappdosing lastappdosing = lastappdosingMapper.getLastappdosing(app_id);
            map.put("app",lastappdosing);
        }
        Integer ownerid = dog.getDogownerId();
        Dogowner dogowner = dogownerMapper.selectByPrimaryKey(ownerid);
        map.put("owner",dogowner);
        return map;
    }

    @Override
    public String modifyDog(Integer dogid, String dogname, String dogsex, String dogweight, String dogcolor, Integer dogage) {
        Dog dog = dogMapper.selectByPrimaryKey(dogid);
        dog.setDogName(dogname);
        dog.setDogSex(dogsex);
        dog.setDogWeight(dogweight);
        dog.setDogColor(dogcolor);
        dog.setDogAge(dogage);
        String res = dogMapper.updateByPrimaryKey(dog)>0?"修改成功！":"修改失败";
        return res;
    }
}
